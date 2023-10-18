package pt.ua.cbd;

import com.mongodb.client.*;
import com.mongodb.client.model.Aggregates;
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
    private static final int limit = 3;
    private static final int timslot = 10;
    public static void main( String[] args )
    {
        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)){
            MongoDatabase database = mongoClient.getDatabase("cbd");
            MongoCollection<Document> collection = database.getCollection("AtendimentoA");

            PrintWriter out = new PrintWriter(new FileWriter("CBD_L204a-out_107457.txt"));
            Scanner sc = new Scanner(System.in);

            while (true) {
                printFunction(out, "Username ('Enter' for quit): ", false, true);
                String username = sc.nextLine().replaceAll("\\s", "");
                printFunction(out, username, true, false);

                if (username.isEmpty()) {
                    break;
                } else {
                    printFunction(out, "Product: ", false, true);
                    String product = sc.nextLine();
                    printFunction(out, product, true, false);
                    double timestamp = System.currentTimeMillis() / 1000.0;

                    Bson filter = Filters.eq("user", username);
                    FindIterable<Document> cursor = collection.find(filter);

                    if (cursor.first() != null) {
                        Bson update = Updates.pullByFilter(Filters.lt("times", timestamp - timslot));
                        UpdateResult result = collection.updateMany(filter, update);

                        int countProds = collection.aggregate(Arrays.asList(
                                Aggregates.match(filter),
                                Aggregates.project(new Document("nProducts", new Document("$size", "$times")))
                        )).first().getInteger("nProducts");

                        if (countProds + 1 <= limit) {
                            Document userDoc = cursor.first();
                            update = Updates.addToSet("times", timestamp);
                            UpdateOptions opt = new UpdateOptions().upsert(true);
                            UpdateResult newProd = collection.updateOne(userDoc, update, opt);

                        } else {
                            printFunction(out, "ERROR: The maximum product limit set for the time window has been exceeded.", true, true);
                        }
                    } else {
                        InsertOneResult newUser = collection.insertOne(new Document()
                                    .append("_id", new ObjectId())
                                    .append("user", username)
                                    .append("times", Arrays.asList(timestamp)));

                    }
                }
            }

            out.close();
            sc.close();

        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    private static void printFunction(PrintWriter writer, String toPrint, boolean println, boolean both) {
        if (println && both) {
            System.out.println(toPrint);
            writer.println(toPrint);
        } else if (!println && both) {
            System.out.print(toPrint);
            writer.print(toPrint);
        } else if (println) {
            writer.println(toPrint);
        } else {
            writer.print(toPrint);
        }
    }
}