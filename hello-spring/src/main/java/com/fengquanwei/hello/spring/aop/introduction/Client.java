package com.fengquanwei.hello.spring.aop.introduction;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 为 Spring bean 引入新接口
 *
 * @author fengquanwei
 * @create 2017/12/29 18:05
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Client {
    @Autowired
    InterfaceA interfaceA;

    @Test
    public void test() {
        interfaceA.methodA();

        // 使用引入的接口
        ((InterfaceB) interfaceA).methodB();

        Assert.assertNotNull(interfaceA);
    }
}
