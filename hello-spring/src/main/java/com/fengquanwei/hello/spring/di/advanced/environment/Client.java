package com.fengquanwei.hello.spring.di.advanced.environment;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 通过 Spring Environment 和属性源注入外部的值
 *
 * @author fengquanwei
 * @create 2017/12/26 16:06
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Client {
    @Autowired
    InterfaceA anInterfaceA;

    @Test
    public void test() {
        anInterfaceA.methodA();
        Assert.assertNotNull(anInterfaceA);
    }
}
