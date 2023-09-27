package cbd.lab01.EX14;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import redis.clients.jedis.Jedis;

public class AutoCompleteA {
    public static String NAMES_KEY = "names";

    public static void main(String[] args) throws IOException {
        Jedis jedis = new Jedis();

        if (jedis.exists(NAMES_KEY)) {
            jedis.del(NAMES_KEY);
        }
        try {
            File file = new File("names.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                jedis.zadd(NAMES_KEY, 0, sc.nextLine());
            }
            sc.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(System.in);

        String search;

        PrintWriter out = new PrintWriter(new FileWriter("CBD-14a-out.txt"));

        while (true) {
            System.out.print("Search for ('Enter' for quit): ");
            out.print("Search for ('Enter' for quit): ");
            search = sc.nextLine();
            if(search.isEmpty()){
                break;
            }

            for(String s : jedis.zrangeByLex(NAMES_KEY, "[" + search , "(" + search + "~" )){
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