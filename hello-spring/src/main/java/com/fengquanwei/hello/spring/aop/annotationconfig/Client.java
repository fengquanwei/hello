package com.fengquanwei.hello.spring.aop.annotationconfig;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基于注解的 AOP（使用 Java Config 配置）
 *
 * @author fengquanwei
 * @create 2017/12/29 18:05
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class) // 【Option1:1】
//@ContextConfiguration(locations = "classpath:spring-aop-annotation.xml") // 【Option1:2】
public class Client {
    @Autowired
    @Qualifier("implementA1")
    InterfaceA interfaceA1;

    @Autowired
    @Qualifier("implementA2")
    InterfaceA interfaceA2;

    @Autowired
    MyAspectA myAspectA;

    @Test
    public void test() {
        interfaceA1.methodA("paramA1");
        interfaceA2.methodA("paramA2");

        System.out.println(myAspectA.getLog());

        Assert.assertNotNull(interfaceA1);
    }
}
