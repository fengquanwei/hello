package com.fengquanwei.hello.spring.rpc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Server
 *
 * @author fengquanwei
 * @create 2018/7/24 11:13
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServerConfig.class)
public class Server {
    @Autowired
    InterfaceA interfaceA;

    @Test
    public void test() throws InterruptedException {
        System.out.println("server start");

        Thread.sleep(10 * 60 * 1000);

        System.out.println("server stop");
    }
}
