package com.fengquanwei.hello.spring.di.advanced.primary;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 配置类
 *
 * @author fengquanwei
 * @create 2017/12/26 16:07
 **/
@Configuration
@ComponentScan
public class Config {
    @Bean
    public InterfaceB interfaceB1() {
        return new ImplementB1();
    }

    @Bean
    @Primary // 首选 Bean
    public InterfaceB interfaceB2() {
        return new ImplementB2();
    }
}
