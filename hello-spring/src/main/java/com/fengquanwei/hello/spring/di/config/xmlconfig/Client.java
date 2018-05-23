package com.fengquanwei.hello.spring.di.config.xmlconfig;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * XML 装配
 *
 * @author fengquanwei
 * @create 2017/12/26 16:06
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-xmlconfig.xml")
public class Client {
    @Autowired
    InterfaceB interfaceB;

    @Test
    public void test() {
        interfaceB.methodB();
        Assert.assertNotNull(interfaceB);
    }
}
