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

        final int timeslot = 20;
        final int limit = 30;

        Jedis jedis = new Jedis();

        Scanner sc = new Scanner(System.in);

        PrintWriter out = new PrintWriter(new FileWriter("CBD-15b-out.txt"));

        while(true){
            System.out.print("Username ('Enter' for quit): ");
            out.print("Username ('Enter' for quit): ");
            String username = sc.nextLine().replaceAll("\\s", "");
            out.println(username);

            if(username.isEmpty()){
                break;
            }else{
                System.out.print("Quantity: ");
                out.print("Quantity: ");
                String request = sc.nextLine().replaceAll("\\s", "");
                out.println(request);

                int quantity = Integer.parseInt(request);

                double timestamp = System.currentTimeMillis() / 1000.0;
                List<Tuple> prods = jedis.zrevrangeWithScores(CLIENTS_PRODUCTS_QUANT + ":" + username, 0, -1);

                for(String user : jedis.smembers(CLIENTS_PRODUCTS_QUANT)){
                    for (String value : jedis.zrange(CLIENTS_PRODUCTS_QUANT + ":" + user, 0, -1)) {
                        if (timestamp - Double.parseDouble(value) > timeslot) {
                            jedis.zrem(CLIENTS_PRODUCTS_QUANT + ":" + user, value);
                        }
                    }
                }

                if(jedis.exists(CLIENTS_PRODUCTS_QUANT + ":" + username)){
                    List<Tuple> products = jedis.zrangeWithScores(CLIENTS_PRODUCTS_QUANT + ":" + username, 0, -1); //[ [produto, quantidade] ]
                    int quantTotal = 0;

                    for(int i = 0; i < products.size(); i++){
                        quantTotal += (int) products.get(i).getScore();
                    }

                    if(quantTotal + quantity <= limit){
                        jedis.zadd(CLIENTS_PRODUCTS_QUANT + ":" + username, quantity, String.valueOf(timestamp));
                    }else if (timestamp - Double.parseDouble(prods.get(prods.size()-1).getElement()) < timeslot){
                        System.out.println("ERROR: The maximum product limit set for the time window has been exceeded.");
                        out.println("ERROR: The maximum product limit set for the time window has been exceeded.");
                    }

                } else {
                    if(quantity <= limit){
                        jedis.zadd(CLIENTS_PRODUCTS_QUANT + ":" + username, quantity, String.valueOf(timestamp));
                        jedis.sadd(CLIENTS_PRODUCTS_QUANT, username);
                    }else{
                        System.out.println("ERROR: The maximum product limit set for the time window has been exceeded.");
                        out.println("ERROR: The maximum product limit set for the time window has been exceeded.");
                    }
                }

                //Print para ver que valores é que estão guardados. Controlar os clientes e produtos
                for(String user : jedis.smembers(CLIENTS_PRODUCTS_QUANT)){
                    System.out.println(user + " - " + jedis.zrevrangeWithScores(CLIENTS_PRODUCTS_QUANT + ":" + user, 0, -1));
                    out.println(user + " - " + jedis.zrevrangeWithScores(CLIENTS_PRODUCTS_QUANT + ":" + user, 0, -1));
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
