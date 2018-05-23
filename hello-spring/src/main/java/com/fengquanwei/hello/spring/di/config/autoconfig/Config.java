package com.fengquanwei.hello.spring.di.config.autoconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类
 *
 * @author fengquanwei
 * @create 2017/12/26 16:07
 **/
@Configuration
@ComponentScan // 默认配置类所在的包为基础包【Option1:1】
//@ComponentScan("com.fengquanwei.hello.spring.di.config.autoconfig") // 指定基础包【Option1:2】
//@ComponentScan(basePackageClasses = Client.class) // 指定基础包，类型安全，对重构友好【Option1:3】
public class Config {
}
