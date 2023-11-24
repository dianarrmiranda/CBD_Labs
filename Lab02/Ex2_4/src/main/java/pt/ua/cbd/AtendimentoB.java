package pt.ua.cbd;

import com.mongodb.client.*;
import com.mongodb.client.model.*;
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

public class AtendimentoB {
    private static final Logger logger = LogManager.getLogger(AtendimentoB.class);
    private static final int limit = 30;
    private static final int timslot = 10;

    public static void main(String[] args) {
        long startTime = System.nanoTime();

        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("cbd");
            MongoCollection<Document> collection = database.getCollection("AtendimentoB");

            PrintWriter out = new PrintWriter(new FileWriter("CBD_L204b-out_107457.txt"));
            Scanner sc = new Scanner(System.in);

            while (true) {
                printFunction(out,"Username ('Enter' for quit): ", false, true );
                String username = sc.nextLine().replaceAll("\\s", "");
                printFunction(out, username, true, false);

                if (username.isEmpty()) {
                    break;
                } else {
                    printFunction(out,"Quantity: ", false, true);
                    int quantity = Integer.parseInt(sc.nextLine());
                    printFunction(out, Integer.toString(quantity), true, false);
                    double timestamp = System.currentTimeMillis() / 1000.0;

                    Bson filter = Filters.eq("user", username);
                    FindIterable<Document> cursor = collection.find(filter).projection(Projections.include("times")).limit(1);

                    if (cursor.first() != null) {

                        Bson update = Updates.pull("products", Filters.lt("time", timestamp - timslot));
                        UpdateResult result = collection.updateOne(filter, update);

                        Document quantidade = collection.aggregate(Arrays.asList(
                                Aggregates.match(filter),
                                Aggregates.unwind("$products"),
                                Aggregates.group("$user", Accumulators.sum("totalQuantity", new Document("$toInt", "$products.quantity"))))
                        ).first();

                        int totalQuant = 0;
                        if (quantidade != null) {
                            totalQuant = quantidade.getInteger("totalQuantity");
                        }
                        if (totalQuant + quantity <= limit) {
                            Document newProd = new Document().append("quantity", quantity).append("time", timestamp);
                            update = Updates.addToSet("products", newProd);
                            UpdateOptions opt = new UpdateOptions().upsert(true);
                            UpdateResult newProdt = collection.updateOne(cursor.first(), update, opt);
                        } else {
                            printFunction(out,"ERROR: The maximum product limit set for the time window has been exceeded.", true, true);
                        }

                    } else {
                        if (quantity <= limit) {
                            InsertOneResult newUser = collection.insertOne(new Document()
                                    .append("_id", new ObjectId())
                                    .append("user", username)
                                    .append("products", Arrays.asList(
                                            new Document()
                                                    .append("quantity", quantity)
                                                    .append("time", timestamp))));
                        }
                        else {
                            printFunction(out,"ERROR: The maximum product limit set for the time window has been exceeded.", true, true);
                        }
                    }
                }
            }
            out.close();
            sc.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        long endTime = System.nanoTime() - startTime;
        System.out.println("Tempo de execução: " + endTime + " nanosegundos");
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
