package pt.ua.cbd;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class AtendimentoB
{
    private static final Logger logger = LogManager.getLogger(AtendimentoB.class);
    private static final int limit = 3;
    private static final int timslot = 5;

    public static void main( String[] args )
    {
        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)){
            MongoDatabase database = mongoClient.getDatabase("cbd");
            MongoCollection<Document> collection = database.getCollection("AtendimentoB");

            PrintWriter out = new PrintWriter(new FileWriter("CBD_L204b-out_107457.txt"));
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.print("Username ('Enter' for quit): ");
                out.print("Username ('Enter' for quit): ");
                String username = sc.nextLine().replaceAll("\\s", "");
                out.println(username);

                if (username.isEmpty()) {
                    break;
                } else {
                    System.out.print("Quantity: ");
                    out.print("Quantity: ");
                    String quantity = sc.nextLine();
                    out.println(quantity);
                    double timestamp = System.currentTimeMillis() / 1000.0;

                    Bson filter = Filters.eq("user", username);
                    FindIterable<Document> cursor = collection.find(filter).projection(Projections.include("times"));

                    if (cursor.first() != null) {

                        Bson filterProds = Filters.and(Filters.eq("user", username), Filters.lt("products.time", timestamp - timslot));
                        UpdateResult updateResult = collection.updateOne(filter, Updates.pull("products", filterProds));

                        Integer totalQuant = collection.aggregate(Arrays.asList(
                                Aggregates.match(filter),
                                Aggregates.unwind("$products"),
                                Aggregates.group("$user", Accumulators.sum("totalQuantity", new Document("$toInt", "$products.quantity"))))).first().getInteger("totalQuantity");


                        if (totalQuant + Integer.parseInt(quantity) <= limit){
                            Document newProd = new Document().append("quantity", quantity).append("time", timestamp);

                            Bson update = Updates.addToSet("products", newProd);
                            UpdateOptions opt = new UpdateOptions().upsert(true);
                            try {
                                UpdateResult newProdt = collection.updateOne(cursor.first(), update, opt);
                            } catch (MongoException me) {
                                System.err.println("Unable to update products due to an error: " + me);
                            }
                        }else {
                            System.out.println("ERROR: The maximum product limit set for the time window has been exceeded.");
                        }

                    } else {
                        try {
                            InsertOneResult newUser = collection.insertOne(new Document()
                                    .append("_id", new ObjectId())
                                    .append("user", username)
                                    .append("products", Arrays.asList(
                                            new Document()
                                                    .append("quantity", quantity)
                                                    .append("time", timestamp))));

                        } catch (MongoException me) {
                            System.err.println("Unable to add new user due to an error: " + me);
                        }

                    }


                }
            }

            out.close();
            sc.close();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
