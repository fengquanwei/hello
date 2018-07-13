package com.fengquanwei.hello.spring.security.web.initializer;

import com.fengquanwei.hello.spring.security.web.config.MyWebConfig;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 配置 DispatcherServlet
 *
 * @author fengquanwei
 * @create 2018/6/4 11:34
 **/
public class MyDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    /**
     * 配置 DispatcherServlet 映射
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    /**
     * 配置 Spring MVC 应用上下文
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyWebConfig.class};
    }
}
