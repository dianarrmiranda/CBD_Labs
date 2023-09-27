package cbd.lab01.EX16;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.resps.Tuple;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class SistMsg {
    public static String SISTMESG_USERS_KEY = "sistMsgUsers";
    public static void main(String[] args) throws IOException {
        Jedis jedis = new Jedis();

        Scanner sc = new Scanner(System.in);

        PrintWriter out = new PrintWriter(new FileWriter("CBD-16a-out.txt"));

        boolean running = true;
        String username = "";

        while(running){
            System.out.println("Menu: ");
            System.out.println("   1 - Add User;");
            System.out.println("   2 - Choose User;");
            System.out.println("   3 - Open Message App;");
            System.out.println("   0 - Exit.");

            out.println("Menu: ");
            out.println("   1 - Add User;");
            out.println("   2 - Choose User;");
            out.println("   3 - Open Message App;");
            out.println("   0 - Exit.");

            String choice = sc.nextLine().replaceAll("\\s", "");
            out.println(choice);

            switch (choice){
                case "1":
                    System.out.print("Username: ");
                    out.print("Username: ");
                    username = sc.nextLine().replaceAll("\\s", "");
                    out.println(username);
                    jedis.sadd(SISTMESG_USERS_KEY, username);
                    break;
                case "2":
                    System.out.println("Users: ");
                    jedis.smembers(SISTMESG_USERS_KEY).forEach(System.out::println);
                    out.println("Users: ");
                    jedis.smembers(SISTMESG_USERS_KEY).forEach(out::println);

                    System.out.print("Select user: ");
                    out.print("Select user: ");
                    username = sc.nextLine().replaceAll("\\s", "");
                    out.println(username);
                    break;

                case "3":
                    boolean appMsg = true;
                    while(appMsg) {
                        System.out.println("Message App (User ON " + username +"): ");
                        System.out.println("   1 - Follow User;");
                        System.out.println("   2 - Sent a message;");
                        System.out.println("   3 - Read messages;");
                        System.out.println("   0 - Go back to the main menu.");

                        out.println("Message App (User ON " + username +"): ");
                        out.println("   1 - Follow User;");
                        out.println("   2 - Sent a message;");
                        out.println("   3 - Read messages;");
                        out.println("   0 - Exit.");

                        choice = sc.nextLine().replaceAll("\\s", "");
                        out.println(choice);

                        switch (choice) {
                            case "1":
                                System.out.println("Users: ");
                                out.println("Users: ");
                                for(String u : jedis.smembers(SISTMESG_USERS_KEY)){
                                    if(!u.equals(username)){
                                        System.out.println(u);
                                        out.println(u);
                                    }
                                }
                                System.out.print("Follow user ('Enter' to go back): ");
                                out.print("Follow user ('Enter' to go back): ");
                                String user = sc.nextLine().replaceAll("\\s", "");
                                out.println(user);
                                if (user.isEmpty()) {
                                    break;
                                }
                                jedis.sadd(  "Followers:" + user, username);
                                break;
                            case "2":
                                System.out.print("Message ('Enter' to go back): ");
                                out.print("Message ('Enter' to go back): ");
                                String msg = sc.nextLine();
                                out.println(msg);
                                if (msg.isEmpty()) {
                                    break;
                                }
                                for (String u : jedis.smembers("Followers:" + username)) {
                                    jedis.lpush("messages:" + u, username + ": " + msg);
                                }
                                break;
                            case "3":
                                System.out.println("Messages: ");
                                jedis.lrange("messages:" + username, 0, -1).forEach(System.out::println);
                                out.println("Messages: ");
                                jedis.lrange("messages:" + username, 0, -1).forEach(out::println);
                                break;
                            case "0":
                                appMsg = false;
                                break;
                        }
                        System.out.println();
                        out.println();
                    }
                    break;
                case "0":
                    System.out.println("Bye!");
                    out.println("Bye!");
                    running = false;
                    break;
            }
            System.out.println();
            out.println();
        }

        out.close();
        sc.close();
        jedis.close();


    }
}
