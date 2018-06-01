package com.fengquanwei.hello.spring.di.advanced.environment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * 配置类
 *
 * @author fengquanwei
 * @create 2017/12/26 16:07
 **/
@Configuration
@PropertySource("classpath:spring-di-environment.properties") // 声明属性源
public class Config {
    @Autowired
    Environment environment;

    @Bean
    public InterfaceA interfaceA() {
        return new ImplementA(environment.getProperty("name")); // 检索属性值
    }
}
