package pt.ua.cbd;

import com.mongodb.client.*;
import com.mongodb.client.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("cbd");
            MongoCollection<Document> collection = database.getCollection("restaurants");

            System.out.println("Alínea a) ");
            System.out.println();
            inserRestaurant(collection);

            searchRestaurants(collection, "nome", "Pizaria Algarvia");

            updateRestaurant(collection, "Pizaria Algarvia", "Pizzaria Algarvia 2");
            deleteRestaurant(collection, "Pizaria Algarvia");

            searchRestaurants(collection, "nome", "Pizzaria Algarvia 2");

            searchRestaurantsWithScoreGreaterThan(collection, 85);

            System.out.println();

            System.out.println("Alínea b) ");
            System.out.println();
            collection.createIndex(new Document("localidade", 1));
            collection.createIndex(new Document("gastronomia", 1));
            collection.createIndex(new Document("nome", "text"));

            searchRestaurants(collection, "localidade", "Bronx");
            System.out.println();
            searchRestaurants(collection, "gastronomia", "Bakery");
            System.out.println();
            searchRestaurantsFilterText(collection, "\"Morris Park Bake Shop\"");

            System.out.println();
            System.out.println("Alínea c) ");

            System.out.println("13. Liste o nome, a localidade, o score e gastronomia dos restaurantes que alcançaram sempre pontuações inferiores ou igual a 3.");
            Bson filter = Filters.nor(Filters.gt("grades.score", 3));
            Bson projection = Projections.include("nome", "localidade", "grades.score", "gastronomia");
            FindIterable<Document> cursor = collection.find(filter).projection(projection);
            for (Document doc : cursor) {
                System.out.println(doc.toJson());
            }
            System.out.println();

            System.out.println("15. Liste o restaurant_id, o nome e os score dos restaurantes nos quais a segunda avaliação foi grade \"A\" e ocorreu em ISODATE \"2014-08-11T00: 00: 00Z\".");
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = dateFormat.parse("2014-08-11T00:00:00Z");

            filter = Filters.and(Filters.eq("grades.1.grade", "A"), Filters.eq("grades.1.date", date));
            projection = Projections.include("restaurant_id", "nome", "grades.score");

            cursor = collection.find(filter).projection(projection);
            for (Document doc : cursor) {
                System.out.println(doc.toJson());
            }

            System.out.println();
            System.out.println("16. Liste o restaurant_id, o nome, o endereço (address) e as coordenadas geográficas (coord) dos restaurantes onde o 2º elemento da matriz de coordenadas tem um valor superior a 42 e inferior ou igual a 52.");

            filter = Filters.and(Filters.gt("address.coord.1", 42), Filters.lte("address.coord.1", 52));
            projection = Projections.include("restaurant_id", "nome", "address");
            cursor = collection.find(filter).projection(projection);
            for (Document doc : cursor) {
                System.out.println(doc.toJson());
            }

            System.out.println();
            System.out.println("19. Indique o número total de avaliações (numGrades) na coleção.");
            List<Bson> pipeline = Arrays.asList(
                    Aggregates.unwind("$grades"),
                    Aggregates.group(null, Accumulators.sum("numGrades", 1)));
            AggregateIterable<Document> cursorAggr = collection.aggregate(pipeline);
            for (Document doc : cursorAggr) {
                System.out.println(doc.toJson());
            }

            System.out.println();
            System.out.println("21. Apresente o número total de avaliações (numGrades) em cada dia da semana");
            pipeline = Arrays.asList(
                    Aggregates.unwind("$grades"),
                    Aggregates.addFields(new Field<>("dayOfWeek", new Document("$dayOfWeek", "$grades.date"))),
                    Aggregates.group("$dayOfWeek", Accumulators.sum("totalGrades", 1)),
                    Aggregates.sort(Sorts.ascending("_id"))
            );
            cursorAggr = collection.aggregate(pipeline);
            for (Document doc : cursorAggr) {
                System.out.println(doc.toJson());
            }
            System.out.println();

            System.out.println("Alínea d) ");
            countLocalidades(collection);

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private static void inserRestaurant(MongoCollection<Document> collection) {
        Document address = new Document()
                .append("building", "1008")
                .append("coord", Arrays.asList(-50.63, 58.5696))
                .append("rua", "Rua Tavira")
                .append("zipcode", "38005");

        List<Document> grades = Arrays.asList(
                new Document()
                        .append("date", new Date())
                        .append("grade", "A")
                        .append("score", 10));

        Document document = new Document()
                .append("address", address)
                .append("localidade", "Algarve")
                .append("gastronomia", "Italian")
                .append("grades", grades)
                .append("nome", "Pizaria Algarvia")
                .append("restaurant_id", "30075555");

        collection.insertOne(document);
        System.out.println("Sucessfully Inserted.");
    }

    public static void updateRestaurant(MongoCollection<Document> collection, String oldName, String newName) {
        Document query = new Document();
        query.put("nome", oldName);
        Document newDocument = new Document();
        newDocument.put("nome", newName);
        Document updateObject = new Document();
        updateObject.put("$set", newDocument);

        collection.updateOne(query, updateObject);
        System.out.println("Sucessfully Updated.");
    }

    public static void searchRestaurants(MongoCollection<Document> collection, String fieldName, String value) {
        Bson filter = Filters.eq(fieldName, value);
        FindIterable<Document> cursor = collection.find(filter);
        System.out.println(cursor.explain().toJson());
        for (Document doc : cursor) {
            System.out.println(doc.toJson());
        }
    }

    public static void searchRestaurantsFilterText(MongoCollection<Document> collection, String value) {
        Bson filter = Filters.text(value);
        FindIterable<Document> cursor = collection.find(filter);
        System.out.println(cursor.explain().toJson());
        for (Document doc : cursor) {
            System.out.println(doc.toJson());
        }
    }

    public static void deleteRestaurant(MongoCollection<Document> collection, String name) {
        Document query = new Document();
        query.put("nome", name);
        collection.deleteOne(query);
    }

    public static void searchRestaurantsWithScoreGreaterThan(MongoCollection<Document> collection, int score) {
        Bson filter = Filters.gt("grades.score", 85);
        FindIterable<Document> cursor = collection.find(filter);
        System.out.println("Listar todos os restaurantes que tenham pelo menos um score superior a " + score + ": ");
        for (Document doc : cursor) {
            System.out.println(doc.toJson());
        }
    }

    public static void countLocalidades(MongoCollection<Document> collection) {
        DistinctIterable<String> distinctLocal = collection.distinct("localidade", String.class);
        long count = StreamSupport.stream(distinctLocal.spliterator(), false).count();
        System.out.println("Numero de localidades distintas: " + count);
    }
}
