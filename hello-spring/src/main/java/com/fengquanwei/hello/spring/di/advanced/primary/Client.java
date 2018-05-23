package com.fengquanwei.hello.spring.di.advanced.primary;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 首选 bean
 *
 * @author fengquanwei
 * @create 2017/12/26 16:06
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class) // 【Option1:1】
//@ContextConfiguration(locations = "classpath:spring-primary.xml") // 【Option1:2】
public class Client {
    @Autowired
    InterfaceA interfaceA;
    @Autowired
    InterfaceB interfaceB;

    @Test
    public void test() {
        interfaceA.methodA();
        interfaceB.methodB();

        Assert.assertNotNull(interfaceA);
        Assert.assertNotNull(interfaceB);
    }
}
