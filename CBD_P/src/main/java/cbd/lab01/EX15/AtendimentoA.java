package cbd.lab01.EX15;
import redis.clients.jedis.Jedis;

import javax.swing.*;
import java.io.IOException;
import java.util.DoubleSummaryStatistics;
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

        while(true){
            System.out.print("Do you want make a request? (Y/N) ");
            String request = sc.next().toLowerCase();
            sc.nextLine();
            if(request.equals("n")){
                break;
            }else if (request.equals("y")) {
                System.out.print("Username: ");
                String username = sc.next();
                sc.nextLine();
                System.out.print("Products (If you want to include multiple products, please list them separated by ','.): ");
                String[] products = sc.nextLine().replaceAll("\\s", "").split(",");

                if (jedis.sismember(CLIENTS_PRODUCTS, username)) {
                    if(jedis.llen(CLIENTS_PRODUCTS + ":" + username) == limit || jedis.llen(CLIENTS_PRODUCTS + ":" + username) + products.length > limit) {
                        System.err.println("ERRO: Excedeu o limite máximo de produtos definido para a janela temporal");
                    }else {
                        jedis.lpush(CLIENTS_PRODUCTS + ":" + username, products);
                    }
                } else {
                    jedis.sadd(CLIENTS_PRODUCTS, username);
                    if(jedis.llen(CLIENTS_PRODUCTS + ":" + username) + products.length < limit){
                        jedis.lpush(CLIENTS_PRODUCTS + ":" + username, products);
                        jedis.expire(CLIENTS_PRODUCTS + ":" + username, 60);
                    }
                }

                for(String user : jedis.smembers(CLIENTS_PRODUCTS)){
                    if(jedis.ttl(CLIENTS_PRODUCTS + ":" + user) == -2){ //Retorna o tempo que a chave ainda tem, se retornar -1 é porque a chave não tem um expire definido, se retornar -2 é pq o tempo de expire já passou
                        jedis.srem(CLIENTS_PRODUCTS, user);
                    }
                }

                System.out.println("Cliente - Produtos solicitados: ");
                for(String user : jedis.smembers(CLIENTS_PRODUCTS)){
                    System.out.println(user + " - " + jedis.lrange(CLIENTS_PRODUCTS + ":" + user, 0, -1));
                }
                System.out.println();
            }
        }

        sc.close();
        jedis.close();
    }
}