package com.fengquanwei.hello.spring.di.advanced.profile;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * profile bean
 *
 * @author fengquanwei
 * @create 2017/12/26 16:06
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class) // 【Option1:1】
//@ContextConfiguration(classes = Config4App.class) // 【Option1:2】
//@ContextConfiguration(locations = "classpath:spring-profile.xml") // 【Option1:3】
@ActiveProfiles({"dev", "prd"}) // 可以同时启动多个环境的配置
public class Client {
    @Autowired(required = false)
    InterfaceA interfaceA;

    @Autowired(required = false)
    InterfaceB interfaceB;

    @Test
    public void test() {
        System.out.println("InterfaceA: " + interfaceA);
        System.out.println("InterfaceB: " + interfaceB);
    }
}
