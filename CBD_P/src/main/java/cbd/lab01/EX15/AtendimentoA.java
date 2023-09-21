package cbd.lab01.EX15;
import redis.clients.jedis.Jedis;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AtendimentoA {
    public static String CLIENTS_PRODUCTS = "clientsProds";
    public static void main(String[] args) throws IOException {

        final int timeslot = 60;
        final int limit = 3;

        Jedis jedis = new Jedis();

        if(jedis.exists(CLIENTS_PRODUCTS)){
            jedis.del(CLIENTS_PRODUCTS);
        }

        Scanner sc = new Scanner(System.in);

        PrintWriter out = new PrintWriter(new FileWriter("CBD-15a-out.txt"));

        while(true){
            System.out.print("Username ('Enter' for quit): ");
            out.print("Username ('Enter' for quit): ");
            String username = sc.next();
            out.println(username);
            sc.nextLine();
            if(username.toLowerCase().equals("enter")){
                break;
            }else{
                System.out.print("Products (If you want to include multiple products, please list them separated by ','.): ");
                out.print("Products (If you want to include multiple products, please list them separated by ','.): ");
                String prods = sc.nextLine();
                out.println(prods);
                String[] products = prods.replaceAll("\\s", "").split(",");

                if (jedis.sismember(CLIENTS_PRODUCTS, username)) {
                    if(jedis.llen(CLIENTS_PRODUCTS + ":" + username) == limit || jedis.llen(CLIENTS_PRODUCTS + ":" + username) + products.length > limit) {
                        System.err.println("ERROR: The maximum product limit set for the time window has been exceeded.\"");
                        out.println("ERROR: The maximum product limit set for the time window has been exceeded.\"");
                    }else {
                        jedis.lpush(CLIENTS_PRODUCTS + ":" + username, products);
                    }
                } else {
                    jedis.sadd(CLIENTS_PRODUCTS, username);
                    if(products.length <= limit){
                        jedis.lpush(CLIENTS_PRODUCTS + ":" + username, products);
                        jedis.expire(CLIENTS_PRODUCTS + ":" + username, 60);
                    }else {
                        System.err.println("ERROR: The maximum product limit set for the time window has been exceeded.\"");
                        out.println("ERROR: The maximum product limit set for the time window has been exceeded.\"");
                    }
                }

                for(String user : jedis.smembers(CLIENTS_PRODUCTS)){
                    if(jedis.ttl(CLIENTS_PRODUCTS + ":" + user) == -2){ //Retorna o tempo que a chave ainda tem, se retornar -1 é porque a chave não tem um expire definido, se retornar -2 é pq o tempo de expire já passou
                        jedis.srem(CLIENTS_PRODUCTS, user);
                    }
                }

                System.out.println("Clients - Products: ");
                out.println("Clients - Products: ");
                for(String user : jedis.smembers(CLIENTS_PRODUCTS)){
                    System.out.println(user + " - " + jedis.lrange(CLIENTS_PRODUCTS + ":" + user, 0, -1));
                    out.println(user + " - " + jedis.lrange(CLIENTS_PRODUCTS + ":" + user, 0, -1));
                }
                System.out.println();
                out.println();

            }
        }

        out.close();
        sc.close();
        jedis.close();
    }
}