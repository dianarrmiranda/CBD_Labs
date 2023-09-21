package cbd.lab01.EX14;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.ZParams;

public class AutoCompleteB {
    public static String NAMESPOP_KEY = "namesPop";
    public static  String NAMESPOP_WITHOUTSCORE = "namesWithoutScore";
    public static  String NAMESPOP_FILTER = "namesPopFilter";
    public static String NAMESPOP_RESULT = "namesPopResult";
    public static void main(String[] args) throws IOException {
        Jedis jedis = new Jedis();

        if (jedis.exists(NAMESPOP_KEY)) {
            jedis.del(NAMESPOP_KEY);
        }

        if (jedis.exists(NAMESPOP_WITHOUTSCORE)) {
            jedis.del(NAMESPOP_WITHOUTSCORE);
        }

        try {
            File file = new File("nomes-pt-2021.csv");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line[] = sc.nextLine().split(";");
                jedis.zadd(NAMESPOP_KEY, Integer.parseInt(line[1]), line[0]);
                jedis.zadd(NAMESPOP_WITHOUTSCORE, 0, line[0]);
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);

        String search;

        PrintWriter out = new PrintWriter(new FileWriter("CBD-14b-out.txt"));

        while (true) {
            System.out.print("Search for ('Enter' for quit): ");
            out.print("Search for ('Enter' for quit): ");
            search = sc.nextLine();
            out.println(search);
            if(search.isEmpty()){
                break;
            }

            if (jedis.exists(NAMESPOP_RESULT)) {
                jedis.del(NAMESPOP_RESULT);
            }

            if (jedis.exists(NAMESPOP_FILTER)) {
                jedis.del(NAMESPOP_FILTER);
            }

            for(String s : jedis.zrangeByLex(NAMESPOP_WITHOUTSCORE, "[" + search , "(" + search + "~" )){
                jedis.zadd(NAMESPOP_FILTER, 0, s);
            }

            ZParams zParams = new ZParams();
            zParams.weights(0,1);
            jedis.zinterstore(NAMESPOP_RESULT, zParams, NAMESPOP_FILTER, NAMESPOP_KEY);

            for(String s : jedis.zrevrange(NAMESPOP_RESULT, 0,-1)){
                System.out.println(s);
                out.println(s);
            }

            System.out.println();
            out.println();

        }

        out.close();
        sc.close();
        jedis.close();
    }

}