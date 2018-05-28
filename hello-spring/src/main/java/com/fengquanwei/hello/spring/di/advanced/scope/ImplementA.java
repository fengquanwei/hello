package com.fengquanwei.hello.spring.di.advanced.scope;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * ImplementA
 *
 * @author fengquanwei
 * @create 2017/12/26 16:05
 **/
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) // 作用域：原型【Option1:1】
//@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.INTERFACES) // 作用域：会话，作用域代理模式：接口【Option1:2】
//@Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS) // 作用域：请求，作用域代理模式：目标类【Option1:3】
public class ImplementA implements InterfaceA {
    @Override
    public void methodA() {
        System.out.println("ImplementA.methodA()");
    }
}
