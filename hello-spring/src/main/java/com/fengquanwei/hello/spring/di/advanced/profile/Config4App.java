package com.fengquanwei.hello.spring.di.advanced.profile;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 配置类
 *
 * @author fengquanwei
 * @create 2017/12/26 16:07
 **/
@Configuration
@Import({Config4Dev.class, Config4Prd.class})
public class Config4App {

}
