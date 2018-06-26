package com.fengquanwei.hello.spring.data.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Client
 *
 * @author fengquanwei
 * @create 2018/6/26 16:30
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Client {
    @Autowired
    UserRepository userRepository;

    /**
     * @CachePut 和 @Cacheable 要分开测试
     */
    @Test
    public void testCache() {
        User user1 = new User("Lask", "Feng", 111);
        User user2 = new User("Lynn", "Li", 222);
        User user3 = new User("nocache", "nocache", 333);

        User insert1 = userRepository.insert(user1);
        User insert2 = userRepository.insert(user2);
        User insert3 = userRepository.insert(user3);

        User select1 = userRepository.select(insert1.getId());
        User select2 = userRepository.select(insert1.getId());
        User select3 = userRepository.select(insert1.getId());
        User select4 = userRepository.select(insert2.getId());
        User select5 = userRepository.select(insert2.getId());
        User select6 = userRepository.select(insert3.getId());
        User select7 = userRepository.select(insert3.getId());

        userRepository.delete(user1);
        userRepository.delete(user2);

        User select8 = userRepository.select(insert1.getId());

        System.out.println("Over");
    }
}
