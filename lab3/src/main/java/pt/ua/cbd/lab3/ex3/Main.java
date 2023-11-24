package pt.ua.cbd.lab3.ex3;

import com.datastax.oss.driver.api.core.CqlSession;
import com.datastax.oss.driver.api.core.cql.ResultSet;
import com.datastax.oss.driver.api.core.cql.Row;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        try (CqlSession session = CqlSession.builder().withKeyspace("lab03_ex2")
                .build()) {

            //Insert User
            session.execute("INSERT INTO user (username, name, email, date) VALUES ('madalena', 'Madalena Silva', 'mada@ua.pt', '2023-11-16 11:25:00.000')");
            System.out.println("User Inserted Successfully");

            ResultSet search = session.execute("SELECT * FROM user WHERE username='madalena'");
            printData("user", search);

            //Update User
            session.execute("UPDATE user SET name='Madalena Almeida Silva' where username='madalena'");
            System.out.println("User Updated Successfully");
            search = session.execute("SELECT * FROM user WHERE username='madalena'");
            printData("user", search);

            //Pesquisa Registos
            System.out.println("Search for video with id=5:");
            search = session.execute("Select * FROM video WHERE id=5");
            printData("video", search);

            // 10. Permitir a pesquisa do rating medio de um video e quantas vezes foi votado;
            System.out.println("Rating medio de um video e quantas vezes foi votado: ");
            System.out.println("Vídeo 1: ");
            search = session.execute("SELECT AVG(rating) AS Media, COUNT(*) AS Total_Votos FROM ratings WHERE video_id=1");
            printData("avgMedia", search);

            // 11. Lista com as Tags existentes e o numero de videos catalogados com cada uma delas;
            System.out.println("Tags existentes e o numero de videos catalogados com cada uma delas: ");
            search = session.execute("SELECT * FROM nvideosbytag");
            printData("nvideosbytag", search);

            // 12. O número de seguidores de um video
            System.out.println("O número de seguidores de um video: ");
            search = session.execute("SELECT video_id, COUNT(follower) AS num_seguidores FROM video_followers WHERE video_id = 1");
            printData("numSeguidores", search);

            // 8. Todos os comentarios (dos videos) que determinado utilizador esta a seguir (following);
            System.out.println("Todos os comentarios (dos videos) que determinado utilizador esta a seguir (following); ");
            search = session.execute("SELECT * FROM commentsbyfollowers WHERE user_id = 'diana'");
            printData("commentsbyfollowers", search);
        }
    }

    public static void printData(String table, ResultSet search) {
        switch (table){
            case "user":
                for (Row r : search){
                    System.out.println("Username: " + r.getString("username"));
                    System.out.println("Name: " + r.getString("name"));
                    System.out.println("Email: " + r.getString("email"));
                    System.out.println("Register Date: " + r.getInstant("date"));
                    System.out.println();
                }
            case "video":
                for (Row r : search){
                    System.out.println("Id: " + r.getInt("id"));
                    System.out.println("Name: " + r.getString("name"));
                    System.out.println("Description: " + r.getString("description"));
                    System.out.println("Author: " + r.getString("author"));
                    System.out.println("Tags: " + r.getSet("tags", String.class));
                    System.out.println("Upload Date: " + r.getInstant("uploadDate"));
                    System.out.println();
                }
            case "avgMedia":
                for (Row r : search){
                    System.out.println("Rating Médio: " + r.getInt("media"));
                    System.out.println("Número de Votos: " + r.getLong("total_votos"));
                    System.out.println();
                }
            case "nvideosbytag":
                for (Row r : search){
                    System.out.println("Tag: " + r.getString("tag") + " - " + "Número de Videos " + r.getInt("nvideos"));
                }
                System.out.println();
            case "numSeguidores":
                for (Row r : search){
                    System.out.println("Id do Video: " + r.getInt("video_id") + " - " + "Número de Seguidores " + r.getLong("num_seguidores"));
                }
            case "commentsbyfollowers":
                for (Row r : search) {
                    System.out.println("Video ID: " + r.getInt("video_id"));
                    System.out.println("Autor: " + r.getString("user_id"));
                    System.out.println("Comments: " + r.getMap("comments", String.class, List.class));

                }
                System.out.println();
        }

    }
}