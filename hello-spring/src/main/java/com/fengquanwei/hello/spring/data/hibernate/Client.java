package com.fengquanwei.hello.spring.data.hibernate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Spring 整合 Hibernate
 *
 * @author fengquanwei
 * @create 2017/12/29 18:05
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Client {
    @Autowired
    UserRepository userRepository;

    /**
     * 增
     */
    @Test
    public void testInsert() {
        User user = new User("Lask", "Feng", 1000);

        int insert = userRepository.insert(user);
        System.out.println("insert: " + insert);
    }

    /**
     * 查
     */
    @Test
    public void testSelect() {
        User user = userRepository.selectOne(23);
        System.out.println("selectOne: " + user);

        List<User> userList = userRepository.selectAll();
        System.out.println("selectAll: " + userList);
    }
}
