package com.fengquanwei.hello.spring.data.springdatajpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Spring Data Jpa
 *
 * @author fengquanwei
 * @create 2018/6/15 15:33
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Client {
    @Autowired
    UserRepository userRepository;

    @Test
    public void testSave() {
        User user = userRepository.save(new User("Lask", "Feng", 1000));
        System.out.println("save: " + user);
    }

    @Test
    public void testFind() {
        List<User> userList = userRepository.findAll();
        System.out.println("findAll: " + userList);

        if (userList != null && userList.size() > 0) {
            User user = userRepository.findByFirstName(userList.get(0).getFirstName());
            System.out.println("findByFirstName: " + user);
        }

        List<User> allFatSalary = userRepository.findAllFatSalary();
        System.out.println("findAllFatSalary: " + allFatSalary);
    }
}
