package com.fengquanwei.hello.spring.di.config.mixedconfig;

/**
 * ImplementA
 *
 * @author fengquanwei
 * @create 2017/12/26 16:05
 **/
public class ImplementA implements InterfaceA {
    @Override
    public void methodA() {
        System.out.println("ImplementA.methodA()");
    }
}
