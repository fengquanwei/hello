package com.fengquanwei.hello.spring.di.config.mixedconfig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * 总配置类
 *
 * @author fengquanwei
 * @create 2017/12/26 20:41
 **/
@Configuration
@Import({ConfigA.class, ConfigB.class}) // 导入 Java Config 配置
@ImportResource("classpath:spring-di-mixedconfig-c.xml") // 导入 XML Config 配置
public class Config {
}
