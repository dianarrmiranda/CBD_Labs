package cbd.lab01.EX14;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import redis.clients.jedis.Jedis;

public class AutoCompleteB {
    public static String NAMESPOP_KEY = "namesPop";

    public static void main(String[] args) throws IOException {
        Jedis jedis = new Jedis();

        if (jedis.exists(NAMESPOP_KEY)) {
            jedis.del(NAMESPOP_KEY);
        }
        try {
            File file = new File("nomes-pt-2021.csv");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line[] = sc.nextLine().split(";");
                jedis.zadd(NAMESPOP_KEY, Integer.parseInt(line[1]), line[0]);
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
            search = sc.next();
            out.println(search);
            if (search.equals("Enter") || search.equals("enter")) {
                break;
            }

            for (String s : jedis.zrevrange(NAMESPOP_KEY, 0, -1)) {
                if (s.toLowerCase().startsWith(search.toLowerCase())) {
                    System.out.println(s);
                    out.println(s);
                }
            }
            System.out.println();
            out.println();

        }

        out.close();
        sc.close();
        jedis.close();
    }

}