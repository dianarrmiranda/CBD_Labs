package pt.ua.cbd.lab4.ex4;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;

import java.io.File;
import java.io.PrintStream;
public class Main {
    public static void main(String[] args) {
        String query;

        try (Driver driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.none())) {
            Session session = driver.session();
            /*
            query = "LOAD CSV WITH HEADERS FROM 'file:///resources/vgsales.csv' AS row " +
                    "MERGE (g:Game {rank: row.Rank, name: row.Name, year: row.Year}) " +
                    "MERGE (genre: Genre {name: row.Genre}) " +
                    "MERGE (g)-[h:HAS_GENRE]->(genre) " +
                    "MERGE (pl:Platform {name: row.Platform}) " +
                    "MERGE (g)-[r:RELEASED_ON]->(pl) " +
                    "MERGE (pub:Publisher {name: row.Publisher}) " +
                    "MERGE (g)-[pb:PUBLISHED_BY]->(pub) " +
                    "MERGE (g)-[s:SALES {NA: row.NA_Sales, EU: row.EU_Sales, JP: row.JP_Sales, Other: row.Other_Sales, Global: row.Global_Sales}]-(pl)";
            //session.run(query); */

            //MAtch(n) detach delete n

            try {
                File file = new File("CBD_L44c_output.txt");
                PrintStream stream = new PrintStream(file);
                System.setOut(stream);

                //1- Géneros por plataforma
                query = "MATCH (pl:Platform)<-[:RELEASED_ON]-(g:Game)-[:HAS_GENRE]->(genre:Genre)" +
                        "WITH pl, COLLECT(DISTINCT genre.name) AS genres\n" +
                        "RETURN pl.name, genres\n" +
                        "ORDER BY SIZE(genres) DESC";

                System.out.println("1 - Géneros por plataforma:");
                System.out.println("|------------|---------------------------------------------------------------------------------------------------------------------|");
                System.out.printf("| %-10s | %-115s |\n", "Plataforma", "Géneros");
                System.out.println("|------------|---------------------------------------------------------------------------------------------------------------------|");
                session.run(query).list().forEach(record -> {
                    System.out.printf("| %-10s | %-115s |\n", record.get("pl.name").asString(), record.get("genres").asList().toString());
                });
                System.out.println();

                //2- Média de vendas totais por ano
                query = "MATCH (g:Game)-[s:SALES]->()\n" +
                        "WITH g, SUM(toFloat(s.Global) + toFloat(s.EU) + toFloat(s.JP) + toFloat(s.NA) + toFloat(s.Other)) AS totalSales\n" +
                        "RETURN g.year, ROUND(AVG(totalSales), 3) AS AvgTotalSales\n" +
                        "ORDER BY AvgTotalSales DESC";

                System.out.println("2- Média de vendas totais por ano");
                System.out.println("|------------|---------------------------------------------------------------------------------------------------------------------|");
                System.out.printf("| %-10s | %-40s |\n", "Ano", "Média de vendas totais (em milhões)");
                System.out.println("|------------|------------------------------------------|");
                session.run(query).list().forEach(record -> {
                    System.out.printf("| %-10s | %-40s |\n", record.get("g.year").asString(), record.get("AvgTotalSales").asNumber());
                });
                System.out.println();


            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}