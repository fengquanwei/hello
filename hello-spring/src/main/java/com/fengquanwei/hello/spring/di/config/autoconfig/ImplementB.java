package com.fengquanwei.hello.spring.di.config.autoconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自动装配
 *
 * @author fengquanwei
 * @create 2018/5/23 10:50
 **/
@Component
public class ImplementB implements InterfaceB {
    @Autowired // 自动装配【Option1:1】
    private InterfaceA interfaceA;

//    @Autowired // 自动装配：构造器注入【Option1:2】
//    public ImplementB(InterfaceA interfaceA) {
//        this.interfaceA = interfaceA;
//    }

//    @Autowired(required = false) // 自动装配：方法注入。使用 required = false 需注意 NPE 问题【Option1:3】
//    public void setInterfaceA(InterfaceA interfaceA) {
//        this.interfaceA = interfaceA;
//    }

//    @Autowired // 自动装配：任意方法都可以注入【Option1:4】
//    public void insertInterfaceA(InterfaceA interfaceA) {
//        this.interfaceA = interfaceA;
//    }

    @Override
    public void methodB() {
        interfaceA.methodA();
        System.out.println("ImplementB.methodB()");
    }
}
