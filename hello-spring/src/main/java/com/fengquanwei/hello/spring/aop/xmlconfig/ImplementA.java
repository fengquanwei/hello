package com.fengquanwei.hello.spring.aop.xmlconfig;

import org.springframework.stereotype.Component;

/**
 * ImplementA
 *
 * @author fengquanwei
 * @create 2018/1/2 15:06
 **/
@Component
public class ImplementA implements InterfaceA {
    @Override
    public void methodA() {
        System.out.println("ImplementA.methodA()");
    }
}
