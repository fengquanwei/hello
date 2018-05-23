package com.fengquanwei.hello.spring.di.advanced.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 配置类
 *
 * @author fengquanwei
 * @create 2017/12/26 16:07
 **/
@Configuration
public class Config {
    @Bean
    @Profile("dev")
    public InterfaceA interfaceA() {
        return new ImplementA();
    }

    @Bean
    @Profile("prd")
    public InterfaceB interfaceB() {
        return new ImplementB();
    }
}
