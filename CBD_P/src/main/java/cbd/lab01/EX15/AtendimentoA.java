package cbd.lab01.EX15;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.resps.Tuple;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class AtendimentoA {
    public static String CLIENTS_KEY = "clientsKey";
    public static void main(String[] args) throws IOException {

        final int timeslot = 10;
        final int limit = 3;

        Jedis jedis = new Jedis();

        if(jedis.exists(CLIENTS_KEY)){
            jedis.del(CLIENTS_KEY);
        }

        Scanner sc = new Scanner(System.in);

        PrintWriter out = new PrintWriter(new FileWriter("CBD-15a-out.txt"));

        while(true){
            System.out.print("Username ('Enter' for quit): ");
            out.print("Username ('Enter' for quit): ");
            String username = sc.nextLine().replaceAll("\\s", "");
            out.println(username);

            if(username.isEmpty()){
                break;
            }else{
                System.out.print("Product: ");
                out.print("Product: ");
                String product = sc.nextLine();
                out.println(product);
                double timestamp = System.currentTimeMillis() / 1000.0;
                List<Tuple> prods = jedis.zrevrangeWithScores(CLIENTS_KEY + ":" + username, 0, -1);

                for (String value : jedis.zrange(CLIENTS_KEY + ":" + username, 0, -1)) {
                    if (timestamp - Double.parseDouble(value) > timeslot) {
                        jedis.zrem(CLIENTS_KEY + ":" + username, value);
                    }
                }

                if(jedis.exists(CLIENTS_KEY + ":" + username)){
                    if(prods.size() + 1 <= limit){
                        jedis.zadd(CLIENTS_KEY + ":" + username, prods.get(0).getScore() + 1, String.valueOf(timestamp));
                    }else if (timestamp - Double.parseDouble(prods.get(prods.size()-1).getElement()) < timeslot)
                        System.out.println("ERROR: The maximum product limit set for the time window has been exceeded.");
                    else {
                        jedis.zadd(CLIENTS_KEY + ":" + username, prods.get(0).getScore() + 1, String.valueOf(timestamp));
                    }
                }else {
                    jedis.zadd(CLIENTS_KEY + ":" + username, 1, String.valueOf(timestamp));
                }

                System.out.println();
                out.println();

            }
        }

        System.out.println(jedis.zrevrangeWithScores(CLIENTS_KEY + ":" + "diana", 0, -1));
        out.close();
        sc.close();
        jedis.close();
    }
}