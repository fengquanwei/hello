package com.fengquanwei.hello.spring.di.advanced.placeholder.xmlconfig;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 占位符（XML 配置）
 *
 * @author fengquanwei
 * @create 2017/12/26 16:06
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-di-placeholder.xml")
public class Client {
    @Autowired
    InterfaceA interfaceA;

    @Test
    public void test() {
        interfaceA.methodA();
        Assert.assertNotNull(interfaceA);
    }
}
