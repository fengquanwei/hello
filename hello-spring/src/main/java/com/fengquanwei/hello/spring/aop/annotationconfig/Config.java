package com.fengquanwei.hello.spring.aop.annotationconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Java Config
 *
 * @author fengquanwei
 * @create 2017/12/29 18:05
 **/
@Configuration
@ComponentScan
@EnableAspectJAutoProxy // 启动切面自动代理
public class Config {
}
