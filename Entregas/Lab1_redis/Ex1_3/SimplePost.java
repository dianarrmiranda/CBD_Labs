package cbd.lab01.EX13;

import redis.clients.jedis.Jedis;

public class SimplePost {
    public static String USERS_KEY = "users"; // Key set for users' name
    public static String USERS_KEY_LIST = "usersList"; // Key List for users' name
    public static String USERS_KEY_HASH = "usersHash"; // Key List for users' name

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        if (jedis.exists(USERS_KEY)) {
            jedis.del(USERS_KEY);
        }
        if (jedis.exists(USERS_KEY_LIST)) {
            jedis.del(USERS_KEY_LIST);
        }
        if (jedis.exists(USERS_KEY_HASH)) {
            jedis.del(USERS_KEY_HASH);
        }
        // some users
        String[] users = { "Ana", "Pedro", "Maria", "Luis" };
        // jedis.del(USERS_KEY); // remove if exists to avoid wrong type
        for (String user : users)
            jedis.sadd(USERS_KEY, user);
        jedis.smembers(USERS_KEY).forEach(System.out::println);

        System.out.println("");
        System.out.println("Lista: ");

        jedis.lpush(USERS_KEY_LIST, users[0]);

        for (int i = 1; i < users.length; i++) {
            jedis.rpush(USERS_KEY_LIST, users[i]);
        }

        jedis.lrange(USERS_KEY_LIST, 0, -1).forEach(System.out::println);

        System.out.println("");
        System.out.println("HashMap: ");

        for (int i = 0; i < users.length; i++) {
            jedis.hset(USERS_KEY_HASH, String.valueOf(i), users[i]);
        }

        jedis.hvals(USERS_KEY_HASH).forEach(System.out::println);

        jedis.close();
    }
}