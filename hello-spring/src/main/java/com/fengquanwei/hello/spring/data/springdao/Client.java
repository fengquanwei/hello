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
        User user = new User(1, "Lask");

        int insert1 = userRepository.insertUser(user);
        System.out.println("insert1: " + insert1);

        // 命名参数
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", 2);
        paramMap.put("name", "Lynn");
        int insert2 = userRepository.insertUser(paramMap);
        System.out.println("insert2: " + insert2);
    }

    /**
     * 改
     */
    @Test
    public void testUpdate() {
        User user = new User(2, "Lily");

        int update = userRepository.updateUser(user);
        System.out.println("update: " + update);
    }

    /**
     * 查
     */
    @Test
    public void testSelect() {
        User user = userRepository.selectOneUser(1);
        System.out.println("select: " + user);
    }

}
