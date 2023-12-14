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

            query = "LOAD CSV WITH HEADERS FROM 'file:///resources/vgsales.csv' AS row " +
                    "MERGE (g:Game {rank: row.Rank, name: row.Name, year: row.Year}) " +
                    "MERGE (genre: Genre {name: row.Genre}) " +
                    "MERGE (g)-[h:HAS_GENRE]->(genre) " +
                    "MERGE (pl:Platform {name: row.Platform}) " +
                    "MERGE (g)-[r:RELEASED_ON]->(pl) " +
                    "MERGE (pub:Publisher {name: row.Publisher}) " +
                    "MERGE (g)-[pb:PUBLISHED_BY]->(pub) " +
                    "MERGE (g)-[s:SALES {NA: row.NA_Sales, EU: row.EU_Sales, JP: row.JP_Sales, Other: row.Other_Sales, Global: row.Global_Sales}]-(pl)";
            session.run(query);

            try {
                File file = new File("CBD_L44c_output.txt");
                PrintStream stream = new PrintStream(file);
                System.setOut(stream);

                //1- Listar os géneros por plataforma
                query = "MATCH (pl:Platform)<-[:RELEASED_ON]-(g:Game)-[:HAS_GENRE]->(genre:Genre)" +
                        "WITH pl, COLLECT(DISTINCT genre.name) AS genres\n" +
                        "RETURN pl.name, genres\n" +
                        "ORDER BY SIZE(genres) DESC";

                System.out.println("1 - Listar os géneros por plataforma.");
                System.out.println("|------------|---------------------------------------------------------------------------------------------------------------------|");
                System.out.printf("| %-10s | %-115s |\n", "Plataforma", "Géneros");
                System.out.println("|------------|---------------------------------------------------------------------------------------------------------------------|");
                session.run(query).list().forEach(record -> {
                    System.out.printf("| %-10s | %-115s |\n", record.get("pl.name").asString(), record.get("genres").asList().toString());
                });
                System.out.println();

                //2- Qual a média de vendas totais por ano?
                query = "MATCH (g:Game)-[s:SALES]->()\n" +
                        "WITH g, SUM(toFloat(s.Global) + toFloat(s.EU) + toFloat(s.JP) + toFloat(s.NA) + toFloat(s.Other)) AS totalSales\n" +
                        "RETURN g.year, ROUND(AVG(totalSales), 3) AS AvgTotalSales\n" +
                        "ORDER BY AvgTotalSales DESC";

                System.out.println("2- Qual a média de vendas totais por ano?");
                System.out.println("|------------|------------------------------------------|");
                System.out.printf("| %-10s | %-40s |\n", "Ano", "Média de vendas totais (em milhões)");
                System.out.println("|------------|------------------------------------------|");
                session.run(query).list().forEach(record -> {
                    System.out.printf("| %-10s | %-40s |\n", record.get("g.year").asString(), record.get("AvgTotalSales").asNumber());
                });
                System.out.println();

                //3- Qual a maior distância entre 2 plataformas?
                query = "MATCH path = shortestPath((p1:Platform)-[*]-(p2:Platform))\n" +
                        "WHERE p1 <> p2\n" +
                        "WITH p1, p2, path, length(path) AS dimensao\n" +
                        "ORDER BY dimensao DESC\n" +
                        "RETURN p1.name, p2.name, dimensao\n" +
                        "LIMIT 1";

                System.out.println("3- Qual a maior distância entre 2 plataformas?");
                System.out.println("|-----------------|-----------------|-----------------|");
                System.out.printf("| %-15s | %-15s | %-15s |\n", "Plataforma 1", "Plataforma 2", "Distância");
                System.out.println("|-----------------|-----------------|-----------------|");
                session.run(query).list().forEach(record -> {
                    System.out.printf("| %-15s | %-15s | %-15s |\n", record.get("p1.name").asString(), record.get("p2.name").asString(), record.get("dimensao").asNumber());
                });
                System.out.println();

                //4- Qual o top 5 dos jogos mais vendidos?
                query = "MATCH (g:Game)-[s:SALES]->()\n" +
                        "WITH g, SUM(toFloat(s.Global) + toFloat(s.EU) + toFloat(s.JP) + toFloat(s.NA) + toFloat(s.Other)) AS totalSales\n" +
                        "RETURN g.name, totalSales\n" +
                        "ORDER BY totalSales DESC\n" +
                        "LIMIT 5";

                System.out.println("4- Qual o top 5 dos jogos mais vendidos?");
                System.out.println("|---------------------------|--------------------------------|");
                System.out.printf("| %-25s | %-30s |\n", "Jogo", "Total de Vendas (em mihões)");
                System.out.println("|---------------------------|--------------------------------|");
                session.run(query).list().forEach(record -> {
                    System.out.printf("| %-25s | %-30s |\n", record.get("g.name").asString(), record.get("totalSales").asNumber());
                });
                System.out.println();

                //5- Listar todos os jogos que foram publicados pela mesma organização e no mesmo ano que o jogo The Sims.
                query = "MATCH(sims:Game {name: 'The Sims'})-[:PUBLISHED_BY]->(pub:Publisher)<-[:PUBLISHED_BY]-(g:Game)\n" +
                        "WITH sims, g, pub\n" +
                        "WHERE sims.year = g.year\n" +
                        "RETURN sims.name, COLLECT(DISTINCT g.name) AS colecao, pub.name";

                System.out.println("5- Listar todos os jogos que foram publicados pela mesma organização e no mesmo ano que o jogo The Sims.");
                session.run(query).list().forEach(record -> {
                    System.out.println("Jogo: " + record.get("sims.name").asString());
                    System.out.println("Organização: " + record.get("pub.name").asString());
                    System.out.println("Jogos do mesmo ano e da mesma organização: ");
                    record.get("colecao").asList().forEach(game -> {
                        System.out.println("      -> " + game.toString());
                    });
                });
                System.out.println();

                //6 - Listar o número de jogos por plataforma (ordem decrescente)
                query = "MATCH (g:Game)-[:RELEASED_ON]->(pl:Platform)\n" +
                        "WITH pl, COUNT(DISTINCT g.name) AS numGames\n" +
                        "RETURN pl.name, numGames\n" +
                        "ORDER BY numGames DESC";

                System.out.println("6 - Listar o número de jogos por plataforma (ordem decrescente)");
                System.out.println("|-----------------|---------------------------|");
                System.out.printf("| %-15s | %-25s |\n", "Plataforma", "Número de Jogos");
                System.out.println("|-----------------|---------------------------|");
                session.run(query).list().forEach(record -> {
                    System.out.printf("| %-15s | %-25s |\n", record.get("pl.name").asString(), record.get("numGames").asNumber());
                });
                System.out.println();

                //7 - Listar os 10 jogos com maior número de jogos derivados por prefixo de Nome
                session.run("CREATE INDEX FOR (g:Game) ON (g.name)");
                query = "MATCH (m1:Game)\n" +
                        "WITH m1, m1.name AS Nome\n" +
                        "MATCH (m2:Game)\n" +
                        "WHERE m2.name Starts WITH Nome AND m1< m2\n" +
                        "WITH m1, COUNT(DISTINCT m2.name) as jogosDer\n" +
                        "RETURN m1.name,jogosDer\n" +
                        "ORDER BY jogosDer Desc\n" +
                        "LIMIT 10";

                System.out.println("7 - Listar os 10 jogos com maior número de jogos derivados por prefixo de Nome");
                System.out.println("|------------------------------------------|---------------------------|");
                System.out.printf("| %-40s | %-25s |\n", "Jogo", "Número de Jogos Derivados");
                System.out.println("|------------------------------------------|---------------------------|");
                session.run(query).list().forEach(record -> {
                    System.out.printf("| %-40s | %-25s |\n", record.get("m1.name").asString(), record.get("jogosDer").asNumber()) ;
                });
                System.out.println();

                //8 - Listar por ano os jogos e a sua plataforma do género Puzzle lançados depois de 2013
                query = "MATCH (genre:Genre {name: 'Puzzle'})<-[:HAS_GENRE]-(g:Game)-[:RELEASED_ON]->(pl:Platform)\n" +
                        "WHERE toInteger(g.year) > 2013\n" +
                        "WITH g.year AS year, COLLECT({gameName: g.name, platformName: pl.name}) AS gameCollection\n" +
                        "RETURN year, gameCollection\n" +
                        "ORDER BY year";

                System.out.println("8 - Listar por ano os jogos e a sua plataforma do género Puzzle lançados depois de 2013");
                System.out.println("|------------|---------------------------------------------------------------------------------------------------------------------|");
                System.out.printf("| %-10s | %-115s |\n", "Ano", "Jogos e Plataformas");
                System.out.println("|------------|---------------------------------------------------------------------------------------------------------------------|");
                session.run(query).list().forEach(record -> {
                    System.out.printf("| %-10s |\n", record.get("year").asString());
                    record.get("gameCollection").asList().forEach(game -> {
                        System.out.printf("| %-10s | %-115s |\n", "", game.toString());
                    });
                });
                System.out.println();

                //9- Listar o total de vendas por local de forma decrescente com base nas vendas globais
                query = "MATCH (g:Game)-[s:SALES]->(p:Platform)\n" +
                        "WITH g, s, SUM(toFloat(s.Global)) as Global\n" +
                        "ORDER BY Global DESC\n" +
                        "RETURN g.name, SUM(toFloat(s.NA)), SUM(toFloat(s.EU)),SUM(toFloat(s.JP)), SUM(toFloat(s.Others))\n" +
                        "LIMIT 20";

                System.out.println("9- Listar os total de vendas por local de forma decrescente com base nas vendas globais");
                System.out.println("|----------------------------------------------------|    Vendas em Milhões                                                                                                              |");
                System.out.println("|----------------------------------------------------|--------------------------------|--------------------------------|--------------------------------|--------------------------------|");
                System.out.printf("| %-50s | %-30s | %-30s | %-30s | %-30s |\n", "Jogo", "NA", "EU", "JP", "Outros");
                System.out.println("|----------------------------------------------------|--------------------------------|--------------------------------|--------------------------------|--------------------------------|");
                session.run(query).list().forEach(record -> {
                    System.out.printf("| %-50s | %-30s | %-30s | %-30s | %-30s |\n", record.get("g.name").asString(), record.get("SUM(toFloat(s.NA))").asNumber(), record.get("SUM(toFloat(s.EU))").asNumber(), record.get("SUM(toFloat(s.JP))").asNumber(), record.get("SUM(toFloat(s.Others))").asNumber());
                });
                System.out.println();

                //10- Listar o número de plataformas e em que plataformas os jogos foram lançados
                query = "MATCH (g:Game)-[:RELEASED_ON]->(pl:Platform)\n" +
                        "RETURN g.name AS Game, COUNT(DISTINCT pl) AS num, COLLECT(DISTINCT pl.name)\n" +
                        "ORDER BY num DESC\n" +
                        "LIMIT 40";

                System.out.println("10- Listar o número de plataformas e em que plataformas os jogos foram lançados");
                System.out.println("|----------------------------------------------------|---------------------------|----------------------------------------------------|");
                System.out.printf("| %-50s | %-25s | %-50s |\n", "Jogo", "Número de Plataformas", "Plataformas");
                System.out.println("|----------------------------------------------------|---------------------------|----------------------------------------------------|");
                session.run(query).list().forEach(record -> {
                    System.out.printf("| %-50s | %-25s | %-50s |\n", record.get("Game").asString(), record.get("num").asNumber(), record.get("COLLECT(DISTINCT pl.name)").asList().toString());
                });


            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}