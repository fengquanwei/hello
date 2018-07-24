package com.fengquanwei.hello.spring.rpc.rmi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Client
 *
 * @author fengquanwei
 * @create 2018/7/24 11:50
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ClientConfig.class)
public class Client {
    @Autowired
    InterfaceA interfaceA;

    @Test
    public void test() {
        System.out.println(interfaceA.methodA("Lask"));
    }
}
