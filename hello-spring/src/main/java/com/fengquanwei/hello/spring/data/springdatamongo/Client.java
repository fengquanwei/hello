package com.fengquanwei.hello.spring.data.springdatamongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Client
 *
 * @author fengquanwei
 * @create 2018/6/20 10:12
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Client {
    @Autowired
    MongoOperations mongo;

    @Test
    public void testSave4Mongo() {
        Contact contact1 = new Contact("name1", "phone1");
        Contact contact2 = new Contact("name2", "phone2");
        Contact contact3 = new Contact("name3", "phone3");
        Contact contact4 = new Contact("name4", "phone4");

        List<Contact> contacts = new ArrayList() {{
            add(contact1);
            add(contact2);
            add(contact3);
            add(contact4);
        }};

        User user = new User(2, "FirstName", "LastName", 666, contacts);

        mongo.save(user, "user");

        System.out.println("save: " + user);
    }

    @Test
    public void testCount4Mongo() {
        long count = mongo.getCollection("user").count();
        System.out.println("count: " + count);
    }

    @Test
    public void testFind4Mongo() {
        User user = mongo.findById(2, User.class);

        System.out.println("find: " + user);
    }
}
