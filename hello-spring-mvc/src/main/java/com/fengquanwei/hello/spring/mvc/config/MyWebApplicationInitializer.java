package com.fengquanwei.hello.spring.mvc.config;

import com.fengquanwei.hello.spring.mvc.servlet.MyServlet;
import org.springframework.web.WebApplicationInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * 在 Java 中注册 Servlet、Filter 和 Listener
 *
 * @author fengquanwei
 * @create 2018/6/4 19:42
 **/
public class MyWebApplicationInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        ServletRegistration.Dynamic myServlet = servletContext.addServlet("myServlet", MyServlet.class);
        myServlet.addMapping("/myServlet");
    }
}
