package com.fengquanwei.hello.spring.mvc.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

/**
 * 扩展 AbstractAnnotationConfigDispatcherServletInitializer 的任意类都会自动地配置 DispatcherServlet 和 ContextLoaderListener
 * DispatcherServlet 使用 getServletConfigClasses 配置创建 Spring MVC 应用上下文，用于加载包含 Web 组件的 bean，如控制器、视图解析器以及处理器映射
 * ContextLoaderListener 使用 getRootConfigClasses 配置创建 Spring 应用上下文，用于加载应用中的其他 bean，通常是驱动应用后端的中间层和数据层组件
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
     * 配置 Spring MVC 应用上下文
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MyWebConfig.class};
    }

    /**
     * 配置 Spring 应用上下文
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{MyRootConfig.class};
    }

    /**
     * 自定义 DispatcherServlet 配置
     */
    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
//        registration.setLoadOnStartup(1);
//        registration.setInitParameter("name","Lask");

        // 上传文件的临时目录
        String location = "/opt/tmp"; // 临时目录
        // 上传文件的最大字节数，默认没有限制
        int maxFileSize = 2097152; // 文件大小不超过 2MB
        // 整个 multipart 的最大字节数，默认没有限制
        int maxRequestSize = 4194304; // 整个请求大小不超过 4MB
        // 上传文件大小达到多少字节时将文件写入到临时目录，默认是 0，表示所有上传的文件都写入磁盘中
        int fileSizeThreshold = 0; // 所有的文件都要写入到磁盘中

        // multipart 配置
        registration.setMultipartConfig(new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold));
    }

    /**
     * 将 Filter 映射到 DispatcherServlet
     */
    @Override
    protected Filter[] getServletFilters() {
        return super.getServletFilters();
    }
}
