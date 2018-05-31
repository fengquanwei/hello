package com.fengquanwei.hello.spring.aop.annotationconfig;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 切面 A
 *
 * @author fengquanwei
 * @create 2017/12/29 17:59
 **/
@Aspect // 定义切面
@Component
public class MyAspectA {
    // 调用日志
    private List<String> log = new ArrayList<>();

    // 定义前置通知且处理参数（切点是接口，所有实现都会被代理）
    @Before("execution(* com.fengquanwei.hello.spring.aop.annotationconfig.InterfaceA.methodA(String)) && args(paramA)")
    public void before(String paramA) {
        System.out.println("MyAspectA.before(" + paramA + ")");
        log.add("InterfaceA.methodA(" + paramA + ")");
    }

    // 定义后置通知不处理参数（切点是实现，指定实现才会被代理）
    @After("execution(* com.fengquanwei.hello.spring.aop.annotationconfig.ImplementA1.methodA(..))")
    public void after() {
        System.out.println("MyAspectA.after()");
    }

    public List<String> getLog() {
        return log;
    }
}
