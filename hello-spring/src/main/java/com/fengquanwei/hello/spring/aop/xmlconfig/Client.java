package com.fengquanwei.hello.spring.aop.xmlconfig;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基于 XML 的 AOP
 *
 * @author fengquanwei
 * @create 2017/12/29 18:05
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-aop-xmlconfig.xml")
public class Client {
    @Autowired
    InterfaceA interfaceA;
    @Autowired
    InterfaceB interfaceB;
    @Autowired
    InterfaceC interfaceC;

    // 由于为 InterfaceA 引入了 InterfaceD，所以需要指定具体的 InterfaceD 实现
    @Autowired
    @Qualifier("implementD")
    InterfaceD interfaceD;

    @Test
    public void test() {
        interfaceA.methodA();
        interfaceB.methodB();
        interfaceC.methodC("Lask");
        interfaceD.methodD();

        // 测试引入
        ((InterfaceD) interfaceA).methodD();

        Assert.assertNotNull(interfaceA);
        Assert.assertNotNull(interfaceB);
        Assert.assertNotNull(interfaceC);
    }
}
