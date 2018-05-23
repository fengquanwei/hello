package com.fengquanwei.hello.spring.di.config.autoconfig;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 基于组件扫描的自动装配
 *
 * @author fengquanwei
 * @create 2017/12/26 16:06
 **/
@RunWith(SpringJUnit4ClassRunner.class) // 创建 Spring 应用上下文
@ContextConfiguration(classes = Config.class) // 配置 Spring 应用上下文：使用 Java Config【Option1:1】
//@ContextConfiguration(locations = "classpath:spring-autoconfig.xml") // 配置 Spring 应用上下文：使用 XML Config【Option1:2】
public class Client {
    // 自动装配
    @Autowired
    InterfaceB interfaceB;

    @Test
    public void test() {
        interfaceB.methodB();
        Assert.assertNotNull(interfaceB);
    }
}
