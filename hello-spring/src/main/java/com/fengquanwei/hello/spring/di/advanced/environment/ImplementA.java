package com.fengquanwei.hello.spring.di.advanced.environment;

/**
 * ImplementA
 *
 * @author fengquanwei
 * @create 2017/12/26 16:05
 **/
public class ImplementA implements InterfaceA {
    private String name;

    public ImplementA(String name) {
        this.name = name;
    }

    @Override
    public void methodA() {
        System.out.println("ImplementA.methodA() name: " + name);
    }
}
