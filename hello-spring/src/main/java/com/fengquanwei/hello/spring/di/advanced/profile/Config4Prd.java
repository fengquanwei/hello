package com.fengquanwei.hello.spring.di.advanced.profile;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Prd 环境配置类
 *
 * @author fengquanwei
 * @create 2017/12/26 16:07
 **/
@Configuration
@Profile("prd")
public class Config4Prd {
    @Bean
    public InterfaceB interfaceB() {
        return new ImplementB();
    }
}
