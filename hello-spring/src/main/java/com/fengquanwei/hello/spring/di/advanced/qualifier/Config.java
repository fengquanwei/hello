package com.fengquanwei.hello.spring.di.advanced.qualifier;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
    @MyQualifier1
    @MyQualifier2
    public InterfaceB interfaceB1() {
        return new ImplementB1();
    }

    @Bean
    @MyQualifier1
    @MyQualifier3
    public InterfaceB interfaceB2() {
        return new ImplementB2();
    }
}
