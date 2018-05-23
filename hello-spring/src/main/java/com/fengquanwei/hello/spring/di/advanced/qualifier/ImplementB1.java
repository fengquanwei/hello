package com.fengquanwei.hello.spring.di.advanced.qualifier;

/**
 * ImplementB1
 *
 * @author fengquanwei
 * @create 2018/5/23 18:38
 **/
public class ImplementB1 implements InterfaceB {
    @Override
    public void methodB() {
        System.out.println("ImplementB1.methodB()");
    }
}
