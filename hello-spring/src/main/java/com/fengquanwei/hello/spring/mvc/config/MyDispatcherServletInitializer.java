package com.fengquanwei.hello.spring.mvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * 扩展 AbstractAnnotationConfigDispatcherServletInitializer 的任意类都会自动地配置 DispatcherServlet 和 ContextLoaderListener
 * DispatcherServlet 使用 getServletConfigClasses 配置创建 Spring 应用上下文，用于加载包含 Web 组件的 bean，如控制器、视图解析器以及处理器映射
 * ContextLoaderListener 使用 getRootConfigClasses 配置创建根应用上下文，用于加载应用中的其他 bean，通常是驱动应用后端的中间层和数据层组件
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

    /**
     * 定义 DispatcherServlet 创建的应用上下文中的 bean
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyWebConfig.class};
    }

    /**
     * 定义 ContextLoaderListener 创建的应用上下文中的 bean
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{MyRootConfig.class};
    }
}
