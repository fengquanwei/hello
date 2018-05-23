package com.fengquanwei.hello.spring.di.config.mixedconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类 B
 *
 * @author fengquanwei
 * @create 2017/12/26 20:38
 **/
@Configuration
public class ConfigB {
    @Bean
    public InterfaceB interfaceB(InterfaceA interfaceA) {
        return new ImplementB(interfaceA);
    }
}
