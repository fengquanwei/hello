package com.fengquanwei.hello.spring.aop.xmlconfig;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 切面
 *
 * @author fengquanwei
 * @create 2017/12/29 17:59
 **/
@Component
public class MyAspect {
    // 前置通知
    public void before() {
        System.out.println("MyAspect.before()");
    }

    // 后置通知
    public void after() {
        System.out.println("MyAspect.after()");
    }

    // 返回通知
    public void afterReturning() {
        System.out.println("MyAspect.afterReturning()");
    }

    // 异常通知
    public void afterThrowing() {
        System.out.println("MyAspect.afterThrowing()");
    }

    // 环绕通知
    public void around(ProceedingJoinPoint pjp) {
        try {
            System.out.println("MyAspect.around() before");

            Object[] args = pjp.getArgs();
            System.out.println("MyAspect.around() args: " + Arrays.toString(args));

            Object result = pjp.proceed();
            System.out.println("MyAspect.around() result: " + result);
        } catch (Throwable throwable) {
            System.out.println("MyAspect.around() throw");
        }

        System.out.println("MyAspect.around() after");
    }

    // 前置通知（带入参）
    public void beforeWithParam(String param) {
        System.out.println("MyAspect.beforeWithParam(" + param + ")");
    }
}
