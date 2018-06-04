package com.fengquanwei.hello.spring.mvc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * Spring 应用上下文配置
 *
 * @author fengquanwei
 * @create 2018/6/4 11:52
 **/
@Configuration
@EnableWebMvc // 启用 Spring MVC
@ComponentScan("com.fengquanwei.hello.spring.mvc")
public class MyWebConfig extends WebMvcConfigurerAdapter {
    /**
     * 配置静态资源处理器
     */
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        // 配置 DispatcherServlet：将对静态资源的请求转发到 Servlet 容器中默认的 Servlet 上，而不是 DispatcherServlet 本身来处理
        configurer.enable();
    }

    /**
     * 配置 JSP 视图解析器
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setExposeContextBeansAsAttributes(true);
        return viewResolver;
    }
}