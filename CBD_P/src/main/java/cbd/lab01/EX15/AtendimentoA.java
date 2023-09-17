package cbd.lab01.EX15;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.DoubleSummaryStatistics;
import java.util.Scanner;

public class AtendimentoA {
    public static String CLIENTS_PRODUCTS = "clientsProds";
    public static void main(String[] args) throws IOException {
        Jedis jedis = new Jedis();

        if(jedis.exists(CLIENTS_PRODUCTS)){
            jedis.del(CLIENTS_PRODUCTS);
        }

        Scanner sc = new Scanner(System.in);

        while(true){
            System.out.println("Do you want make a request? (Y/N) ");
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
                    jedis.lpush(CLIENTS_PRODUCTS + ":" + username, products);
                } else {
                    jedis.sadd(CLIENTS_PRODUCTS, username);
                    jedis.lpush(CLIENTS_PRODUCTS + ":" + username, products);
                    jedis.expire(CLIENTS_PRODUCTS + ":" + username, 60);
                }

                for(String user : jedis.smembers(CLIENTS_PRODUCTS)){
                    if(jedis.ttl(CLIENTS_PRODUCTS + ":" + user) == -2){ //Retorna o tempo que a chave ainda tem, se retornar -1 é porque a chave não tem um expire definido, se retornar -2 é pq o tempo de expire já passou
                        jedis.srem(CLIENTS_PRODUCTS, user);
                    }
                }

                System.out.println(jedis.smembers(CLIENTS_PRODUCTS));
            }
        }

        sc.close();
        jedis.close();
    }
}