package com.fengquanwei.hello.spring.security.web.initializer;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 配置 DelegatingFilterProxy
 * Servlet 上下文中的 DelegatingFilterProxy 把 Filter 的处理逻辑委托给 Spring 应用上下文所定义的一个代理 Filter bean
 *
 * @author fengquanwei
 * @create 2018/7/12 11:24
 **/
public class MySecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

}
