package com.fengquanwei.hello.spring.data.springdao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * DB 配置
 *
 * @author fengquanwei
 * @create 2017/12/29 18:05
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
@ActiveProfiles("datasource-dbcp")
public class Client {
    @Autowired
    UserRepository userRepository;

    /**
     * 增
     */
    @Test
    public void testInsert() {
        // 索引参数
        User user = new User(11, "Lask", "Feng", 1000);

        int insert1 = userRepository.insert(user);
        System.out.println("insert1: " + insert1);

        // 命名参数
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", 22);
        paramMap.put("firstName", "Lynn");
        paramMap.put("lastName", "Li");
        paramMap.put("salary", 1000);
        int insert2 = userRepository.insert(paramMap);
        System.out.println("insert2: " + insert2);
    }

    /**
     * 改
     */
    @Test
    public void testUpdate() {
        User user = new User(22, "Lily", "Liu", 666);

        int update = userRepository.update(user);
        System.out.println("update: " + update);
    }

    /**
     * 查
     */
    @Test
    public void testSelect() {
        User user = userRepository.selectOne(11);
        System.out.println("select: " + user);
    }

}
