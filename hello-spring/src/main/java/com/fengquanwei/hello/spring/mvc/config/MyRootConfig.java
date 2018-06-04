package com.fengquanwei.hello.spring.mvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * 根应用上下文配置
 *
 * @author fengquanwei
 * @create 2018/6/4 11:52
 **/
@Configuration
@ComponentScan(basePackages = {"com.fengquanwei.hello.spring.mvc"}, excludeFilters = {@ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class MyRootConfig {
}
