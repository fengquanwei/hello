package com.fengquanwei.hello.spring.aop.annotationconfig;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 切面 B
 *
 * @author fengquanwei
 * @create 2017/12/29 17:59
 **/
@Aspect // 定义切面
@Component
public class MyAspectB {
    // 定义切点（不带参数）
    @Pointcut("execution(* com.fengquanwei.hello.spring.aop.annotationconfig.InterfaceA.methodA(..))")
    public void myPointcut() {
    }

    // 定义切点（处理参数）
    @Pointcut("execution(* com.fengquanwei.hello.spring.aop.annotationconfig.InterfaceA.methodA(String)) && args(paramA)")
    public void myPointcutWithParam(String paramA) {
    }

    // 定义环绕通知
    @Around("myPointcut()")
    public void around(ProceedingJoinPoint pjp) {
        System.out.println("MyAspectB.around() before");

        try {
            // 入参
            Object[] args = pjp.getArgs();
            System.out.println("MyAspectB.around() args: " + Arrays.toString(args));

            // 结果
            Object result = pjp.proceed();
            System.out.println("MyAspectB.around() result: " + result);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("MyAspectB.around() after");
    }

    @AfterReturning("myPointcutWithParam(paramA)")
    public void afterReturning(String paramA) {
        System.out.println("MyAspectB.afterReturning(" + paramA + ")");
    }

    @AfterThrowing("myPointcut()")
    public void afterThrowing() {
        System.out.println("MyAspectB.afterThrowing()");
    }
}
