package cbd.lab01.EX15;

import redis.clients.jedis.Jedis;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class AtendimentoB {
    public static String CLIENTS_PRODUCTS_QUANT = "clientsProdsQuant";
    public static void main(String[] args) throws IOException {

        final int timeslot = 60;
        final int limit = 30;

        Jedis jedis = new Jedis();

        if(jedis.exists(CLIENTS_PRODUCTS_QUANT)){
            jedis.del(CLIENTS_PRODUCTS_QUANT);
        }

        Scanner sc = new Scanner(System.in);

        PrintWriter out = new PrintWriter(new FileWriter("CBD-15b-out.txt"));

        while(true){
            System.out.print("Username ('Enter' for quit): ");
            out.print("Username ('Enter' for quit): ");
            String username = sc.next();
            out.println(username);
            sc.nextLine();
            if(username.toLowerCase().equals("enter")){
                break;
            }else{
                System.out.print("Products (Insert this way: product - quantity. If you want to include multiple products, please list them separated by ','.): ");
                out.print("Products (Insert this way: product - quantity. If you want to include multiple products, please list them separated by ','.): ");
                String prods = sc.nextLine();
                out.println(prods);
                String[] products = prods.replaceAll("\\s", "").split(",");

                if (jedis.sismember(CLIENTS_PRODUCTS_QUANT, username)) {
                    if(jedis.llen(CLIENTS_PRODUCTS_QUANT + ":" + username) == limit || jedis.llen(CLIENTS_PRODUCTS_QUANT + ":" + username) + products.length > limit) {
                        System.err.println("ERROR: The maximum product limit set for the time window has been exceeded.\"");
                        out.println("ERROR: The maximum product limit set for the time window has been exceeded.\"");
                    }else {
                        jedis.lpush(CLIENTS_PRODUCTS_QUANT + ":" + username, products);
                    }
                } else {
                    jedis.sadd(CLIENTS_PRODUCTS_QUANT, username);
                    if(jedis.llen(CLIENTS_PRODUCTS_QUANT + ":" + username) + products.length <= limit){
                        jedis.lpush(CLIENTS_PRODUCTS_QUANT + ":" + username, products);
                        jedis.expire(CLIENTS_PRODUCTS_QUANT + ":" + username, 60);
                    }else {
                        System.err.println("ERROR: The maximum product limit set for the time window has been exceeded.\"");
                        out.println("ERROR: The maximum product limit set for the time window has been exceeded.\"");
                    }
                }

                for(String user : jedis.smembers(CLIENTS_PRODUCTS_QUANT)){
                    if(jedis.ttl(CLIENTS_PRODUCTS_QUANT + ":" + user) == -2){ //Retorna o tempo que a chave ainda tem, se retornar -1 é porque a chave não tem um expire definido, se retornar -2 é pq o tempo de expire já passou
                        jedis.srem(CLIENTS_PRODUCTS_QUANT, user);
                    }
                }

                System.out.println("Clients - Products: ");
                out.println("Clients - Products: ");
                for(String user : jedis.smembers(CLIENTS_PRODUCTS_QUANT)){
                    System.out.println(user + " - " + jedis.lrange(CLIENTS_PRODUCTS_QUANT + ":" + user, 0, -1));
                    out.println(user + " - " + jedis.lrange(CLIENTS_PRODUCTS_QUANT + ":" + user, 0, -1));
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
