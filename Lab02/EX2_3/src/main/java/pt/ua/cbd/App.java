package pt.ua.cbd;

import com.mongodb.client.*;
import com.mongodb.client.model.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.StreamSupport;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);

    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017";

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("cbd");
            MongoCollection<Document> collection = database.getCollection("restaurants");

            System.out.println("Alínea a) ");
            alineaA(collection);
            System.out.println();

            System.out.println("Alínea b) ");
            alineaB(collection);
            System.out.println();

            System.out.println("Alínea c) ");
            alineaC(collection);
            System.out.println();

            System.out.println("Alínea d) ");
            alineaD(collection);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static void alineaA(MongoCollection<Document> collection){

        insertRestaurant(collection);
        System.out.println("Sucessfully Inserted.");
        FindIterable<Document> c = searchRestaurants(collection, "nome", "Pizaria Algarvia");
        c = searchRestaurants(collection, "nome", "Pizaria Algarvia");
        for (Document doc : c) {
            System.out.println(doc.toJson());
        }
        updateRestaurant(collection, "Pizaria Algarvia", "Pizzaria Algarvia 2");
        System.out.println("Sucessfully Updated.");
        deleteRestaurant(collection, "Pizaria Algarvia");

        c = searchRestaurants(collection, "nome", "Pizzaria Algarvia 2");
        for (Document doc : c) {
            System.out.println(doc.toJson());
        }

        int score = 85;
        c = searchRestaurantsWithScoreGreaterThan(collection, score);
        System.out.println("Listar todos os restaurantes que tenham pelo menos um score superior a " + score + ": ");
        for (Document doc : c) {
            System.out.println(doc.toJson());
        }
    }
    private static void alineaB(MongoCollection<Document> collection){
        collection.dropIndexes();
        System.out.println("Execution without indexes:");
        FindIterable<Document> c = searchRestaurants(collection, "localidade", "Bronx");
        System.out.println("Execution Time (localidade): " + c.explain().toBsonDocument().get("executionStats").asDocument().get("executionTimeMillis").asInt32().getValue());

        c = searchRestaurants(collection, "gastronomia", "Bakery");
        System.out.println("Execution Time (gastronomia): " + c.explain().toBsonDocument().get("executionStats").asDocument().get("executionTimeMillis").asInt32().getValue());

        c = searchRestaurantsFilter(collection, "\"Morris Park Bake Shop\"");
        System.out.println("Execution Time (nome_text): " + c.explain().toBsonDocument().get("executionStats").asDocument().get("executionTimeMillis").asInt32().getValue());

        System.out.println();
        collection.createIndex(Indexes.ascending("localidade"));
        collection.createIndex(Indexes.ascending("gastronomia"));
        collection.createIndex(Indexes.text("nome"));
        System.out.println("Execution with indexes:");

        c = searchRestaurants(collection, "localidade", "Bronx");
        System.out.println("Execution Time (localidade): " + c.explain().toBsonDocument().get("executionStats").asDocument().get("executionTimeMillis").asInt32().getValue());

        c = searchRestaurants(collection, "gastronomia", "Bakery");
        System.out.println("Execution Time (gastronomia): " + c.explain().toBsonDocument().get("executionStats").asDocument().get("executionTimeMillis").asInt32().getValue());

        c = searchRestaurantsFilter(collection, "\"Morris Park Bake Shop\"");
        System.out.println("Execution Time (nome_text): " + c.explain().toBsonDocument().get("executionStats").asDocument().get("executionTimeMillis").asInt32().getValue());

    }
    private static void alineaC(MongoCollection<Document> collection) throws ParseException {
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
    }
    private static void alineaD(MongoCollection<Document> collection) throws IOException {
        PrintWriter out = new PrintWriter(new FileWriter("CBD_L203_<107457>.txt"));

        System.out.println("Numero de localidades distintas: " + countLocalidades(collection));
        out.println("Numero de localidades distintas: " + countLocalidades(collection));

        System.out.println();
        out.println();

        System.out.println("Número de restaurantes por localidade:");
        out.println("Número de restaurantes por localidade:");

        Map<String, Integer> locals = countRestByLocalidade(collection);
        for (String s : locals.keySet()) {
            System.out.println("-> " + s + " - " + locals.get(s));
            out.println("-> " + s + " - " + locals.get(s));
        }

        System.out.println();
        out.println();

        String s = "Park";
        System.out.println("Nome de restaurantes contendo '" + s + "' no nome:");
        out.println("Nome de restaurantes contendo '" + s + "' no nome:");

        List<String> nameCloser = getRestWithNameCloserTo(collection,s);
        for (String name : nameCloser) {
            System.out.println("-> " + name);
            out.println("-> " + name);
        }

        out.close();
    }
    private static void insertRestaurant(MongoCollection<Document> collection) {
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

    }

    public static void updateRestaurant(MongoCollection<Document> collection, String oldName, String newName) {
        Document query = new Document();
        query.put("nome", oldName);
        Document newDocument = new Document();
        newDocument.put("nome", newName);
        Document updateObject = new Document();
        updateObject.put("$set", newDocument);

        collection.updateOne(query, updateObject);
    }

    public static FindIterable<Document> searchRestaurants(MongoCollection<Document> collection, String fieldName, String value) {
        Bson filter = Filters.eq(fieldName, value);
        FindIterable<Document> cursor = collection.find(filter);
        return cursor;
    }

    public static FindIterable<Document> searchRestaurantsFilter(MongoCollection<Document> collection, String value) {
        Bson filter = Filters.regex("nome", value);
        FindIterable<Document> cursor = collection.find(filter);
        return cursor;
    }

    public static void deleteRestaurant(MongoCollection<Document> collection, String name) {
        Document query = new Document();
        query.put("nome", name);
        collection.deleteOne(query);
    }

    public static FindIterable<Document> searchRestaurantsWithScoreGreaterThan(MongoCollection<Document> collection, int score) {
        Bson filter = Filters.gt("grades.score", 85);
        FindIterable<Document> cursor = collection.find(filter);
        return cursor;
    }

    public static int countLocalidades(MongoCollection<Document> collection) {
        DistinctIterable<String> distinctLocal = collection.distinct("localidade", String.class);
        long count = StreamSupport.stream(distinctLocal.spliterator(), false).count();
        return (int) (count);
    }

    public static Map<String, Integer> countRestByLocalidade(MongoCollection<Document> collection) {
        List<Bson> pipeline = Arrays.asList(
                Aggregates.unwind("$localidade"),
                Aggregates.group("$localidade", Accumulators.sum("totalLocal", 1))
        );
        AggregateIterable<Document> cursorAggr = collection.aggregate(pipeline);

        Map<String, Integer> locals = new HashMap<>();
        for (Document doc : cursorAggr) {
            locals.put(doc.getString("_id"), doc.getInteger("totalLocal"));
        }
        return locals;
    }

    public static List<String> getRestWithNameCloserTo(MongoCollection<Document> collection, String name){
        Bson filter = Filters.regex("nome", "Park");
        FindIterable<Document> cursor = collection.find(filter);
        List<String> nameCloser = new ArrayList<>();
        for (Document doc : cursor) {
            nameCloser.add(doc.getString("nome"));
        }
        return nameCloser;
    }


}
