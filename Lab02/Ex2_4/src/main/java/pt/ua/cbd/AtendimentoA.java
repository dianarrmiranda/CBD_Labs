package pt.ua.cbd;

import com.mongodb.MongoException;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.model.Updates;
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

public class AtendimentoA
{
    private static final Logger logger = LogManager.getLogger(AtendimentoA.class);
    public static void main( String[] args )
    {
        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)){
            MongoDatabase database = mongoClient.getDatabase("cbd");
            MongoCollection<Document> collection = database.getCollection("servAtendimento");

            PrintWriter out = new PrintWriter(new FileWriter("CBD_L204a-out_107457.txt"));
            Scanner sc = new Scanner(System.in);

            while (true) {
                System.out.print("Username ('Enter' for quit): ");
                out.print("Username ('Enter' for quit): ");
                String username = sc.nextLine().replaceAll("\\s", "");
                out.println(username);

                if (username.isEmpty()) {
                    break;
                } else {
                    System.out.print("Product: ");
                    out.print("Product: ");
                    String product = sc.nextLine();
                    out.println(product);
                    double timestamp = System.currentTimeMillis() / 1000.0;

                    Bson filter = Filters.eq("user", username);
                    FindIterable<Document> cursor = collection.find(filter);

                    if (cursor.first() != null) {
                        Document userDoc = cursor.first();

                        Bson update = Updates.addToSet("times", timestamp);
                        UpdateOptions opt = new UpdateOptions().upsert(true);
                        try {
                            UpdateResult newProd = collection.updateOne(userDoc, update, opt);
                        } catch (MongoException me) {
                            System.err.println("Unable to update products due to an error: " + me);
                        }
                    } else {
                        try {
                            InsertOneResult newUser = collection.insertOne(new Document()
                                    .append("_id", new ObjectId())
                                    .append("user", username)
                                    .append("times", Arrays.asList(timestamp)));
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
