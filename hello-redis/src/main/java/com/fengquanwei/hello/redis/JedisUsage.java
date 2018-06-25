package com.fengquanwei.hello.redis;

import redis.clients.jedis.Jedis;

/**
 * JedisUsage
 *
 * @author fengquanwei
 * @create 2018/6/25 13:19
 **/
public class JedisUsage {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
        System.out.println(jedis.ping());

        jedis.set("name", "Lask");
        System.out.println(jedis.get("name"));

        jedis.rpush("list", "Apple");
        jedis.rpush("list", "Pear");
        jedis.rpush("list", "Banana");
        System.out.println(jedis.lrange("list", 0, -1));

        System.out.println(jedis.keys("*"));
    }
}
