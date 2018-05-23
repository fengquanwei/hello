package com.fengquanwei.hello.spring.di.context;

import com.fengquanwei.hello.spring.di.config.autoconfig.Config;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * 应用上下文
 *
 * @author fengquanwei
 * @create 2018/5/22 19:29
 **/
public class ApplicationContextUsage {
    public static void main(String[] args) {
        ApplicationContext applicationContext = null;

        // 从类路径下的一个或多个 XML 配置文件中加载上下文定义
//        applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//        // 从文件系统下的一个或多个 XML 配置文件中加载上下文定义
//        applicationContext = new FileSystemXmlApplicationContext("/opt/config/applicationContext.xml");

        // 从一个或多个基于 Java 的配置类中加载应用上下文
        applicationContext = new AnnotationConfigApplicationContext(Config.class);

        System.out.println(applicationContext);
    }
}