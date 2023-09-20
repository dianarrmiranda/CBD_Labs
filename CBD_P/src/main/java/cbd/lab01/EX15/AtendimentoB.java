package cbd.lab01.EX15;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.resps.Tuple;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

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
                System.out.print("Products (Insert product - quantity): ");
                out.print("Products (Insert product - quantity): ");
                String request = sc.nextLine().replaceAll("\\s", "");
                out.println(request);
                String product = request.split("-")[0];
                int quantity = Integer.parseInt(request.split("-")[1]);

                if (jedis.sismember(CLIENTS_PRODUCTS_QUANT, username)) {
                    List<Tuple> products = jedis.zrangeWithScores(CLIENTS_PRODUCTS_QUANT + ":" + username, 0, -1); //[ [produto, quantidade] ]
                    int total = 0;
                    for(int i = 0; i < products.size(); i++){
                        total += (int) products.get(i).getScore();
                    }
                    total += quantity;
                    if(total > limit) {
                        System.err.println("ERROR: The maximum product limit set for the time window has been exceeded.");
                        out.println("ERROR: The maximum product limit set for the time window has been exceeded.");
                    }else {
                        if(jedis.zscore(CLIENTS_PRODUCTS_QUANT + ":" + username, product) != null){
                            double q = jedis.zscore(CLIENTS_PRODUCTS_QUANT + ":" + username, product);
                            jedis.zadd(CLIENTS_PRODUCTS_QUANT + ":" + username, quantity + (int) q, product);
                        }else{
                            jedis.zadd(CLIENTS_PRODUCTS_QUANT + ":" + username, quantity, product);
                        }
                    }

                } else {
                    jedis.sadd(CLIENTS_PRODUCTS_QUANT, username);
                    if(quantity <= limit){
                        jedis.zadd(CLIENTS_PRODUCTS_QUANT + ":" + username, quantity, product);
                        jedis.expire(CLIENTS_PRODUCTS_QUANT + ":" + username, 60);
                    }else {
                        System.err.println("ERROR: The maximum product limit set for the time window has been exceeded.");
                        out.println("ERROR: The maximum product limit set for the time window has been exceeded.");
                    }
                }

                for(String user : jedis.smembers(CLIENTS_PRODUCTS_QUANT)){
                    if(jedis.ttl(CLIENTS_PRODUCTS_QUANT + ":" + user) == -2){ //Retorna o tempo que a chave ainda tem, se retornar -1 é porque a chave não tem um expire definido, se retornar -2 é pq o tempo de expire já passou
                        jedis.srem(CLIENTS_PRODUCTS_QUANT, user);
                    }
                }

                //Print para ver que valores é que estão guardados. Controlar os clientes e produtos
                System.out.println("Clients - Products: ");
                out.println("Clients - Products: ");
                for(String user : jedis.smembers(CLIENTS_PRODUCTS_QUANT)){
                    System.out.println(user + " - " + jedis.zrangeWithScores(CLIENTS_PRODUCTS_QUANT + ":" + user, 0, -1));
                    out.println(user + " - " + jedis.zrangeWithScores(CLIENTS_PRODUCTS_QUANT + ":" + user, 0, -1));
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
