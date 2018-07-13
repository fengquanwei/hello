package com.fengquanwei.hello.spring.security.method.config;

import com.fengquanwei.hello.spring.security.method.evaluator.MyPermissionEvaluator;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

/**
 * MyMethodSecurityConfiguration
 *
 * @author fengquanwei
 * @create 2018/7/13 15:25
 **/
@Configuration
@ComponentScan("com.fengquanwei.hello.spring.security.method")
@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用基于注解的方法安全性
public class MyMethodSecurityConfig extends GlobalMethodSecurityConfiguration {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("Lask").password("Lask").roles("User");
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
        expressionHandler.setPermissionEvaluator(new MyPermissionEvaluator()); // 设置自定义许可计算器的处理器
        return expressionHandler;
    }
}
