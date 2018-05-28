package com.fengquanwei.hello.spring.di.advanced.scope;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * bean 作用域
 *
 * @author fengquanwei
 * @create 2017/12/26 16:06
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Client {
    @Autowired
    InterfaceA interfaceA1;
    @Autowired
    InterfaceA interfaceA2;

    @Test
    public void test() {
        interfaceA1.methodA();
        interfaceA2.methodA();
        Assert.assertNotEquals(interfaceA1, interfaceA2);
    }
}
