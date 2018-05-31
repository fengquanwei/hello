package com.fengquanwei.hello.spring.aop.annotationconfig;

import org.springframework.stereotype.Component;

/**
 * ImplementA1
 *
 * @author fengquanwei
 * @create 2017/12/26 16:05
 **/
@Component
public class ImplementA1 implements InterfaceA {
    @Override
    public String methodA(String paramA) {
        System.out.println("ImplementA1.methodA(" + paramA + ")");
        return "result1";
    }
}
