package com.fengquanwei.hello.spring.di.config.mixedconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类 A
 *
 * @author fengquanwei
 * @create 2017/12/26 16:07
 **/
@Configuration
public class ConfigA {
    @Bean
    public InterfaceA interfaceA() {
        return new ImplementA();
    }
}
