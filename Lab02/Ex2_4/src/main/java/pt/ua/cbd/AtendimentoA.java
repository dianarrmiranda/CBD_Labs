package pt.ua.cbd;

import com.mongodb.DocumentToDBRefTransformer;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;

public class AtendimentoA
{
    private static final Logger logger = LogManager.getLogger(AtendimentoA.class);
    public static void main( String[] args )
    {
        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)){
            MongoDatabase database = mongoClient.getDatabase("cbd");
            MongoCollection<Document> collection = database.getCollection("restaurants");

        } catch (Exception e){
            System.err.println(e);
        }
    }
}
