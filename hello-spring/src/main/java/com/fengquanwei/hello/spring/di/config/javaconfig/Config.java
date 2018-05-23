package com.fengquanwei.hello.spring.di.config.javaconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 *
 * @author fengquanwei
 * @create 2017/12/26 16:07
 **/
@Configuration // 配置类
public class Config {
    @Bean // 声明组件，默认的 Bean ID 是方法名【Option1:1】
//    @Bean(name = "customBeanId") // 声明组件，指定 Bean ID【Option1:2】
    public InterfaceA interfaceA() {
        return new ImplementA();
    }

    @Bean // 注入组件【Option2:1】
    public InterfaceB interfaceB(InterfaceA interfaceA) {
        return new ImplementB(interfaceA);
    }

//    @Bean // 注入组件【Option2:2】
//    public InterfaceB interfaceB() {
//        return new ImplementB(interfaceA()); // Spring 会拦截对被 @Bean 注解的方法的调用，确保直接返回该方法所创建的实例
//    }
}
