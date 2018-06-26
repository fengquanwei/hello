package com.fengquanwei.hello.spring.data.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Client
 *
 * @author fengquanwei
 * @create 2018/6/25 14:28
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Client {
    @Autowired
    RedisTemplate userRedisTemplate;
    @Autowired
    RedisTemplate stringRedisTemplate;

    @Test
    public void testUserRedisTemplate() {
        // 使用简单的值
        userRedisTemplate.opsForValue().set(1, new User(1, "Lask", "Feng", 111));
        Object user = userRedisTemplate.opsForValue().get(1);
        System.out.println(user);

        // 使用 List 类型的值
        userRedisTemplate.opsForList().rightPush("userList", new User(2, "Lynn", "Li", 222));
        userRedisTemplate.opsForList().rightPush("userList", new User(3, "Kyle", "Sun", 333));
        List userList = userRedisTemplate.opsForList().range("userList", 0, -1);
        System.out.println(userList);

        // 绑定到某个 key 上
        BoundListOperations userBoundListOps = userRedisTemplate.boundListOps("userList");
        Long push = userBoundListOps.rightPush(new User(4, "Lucy", "Li", 444));
        System.out.println(push);
        userList = userBoundListOps.range(0, -1);
        System.out.println(userList);
    }

    @Test
    public void testStringRedisTemplate() {
        stringRedisTemplate.opsForValue().set("5", new User(5, "Lily", "Huang", 555).toString());
        Object user = stringRedisTemplate.opsForValue().get("5");
        System.out.println(user);
    }
}
