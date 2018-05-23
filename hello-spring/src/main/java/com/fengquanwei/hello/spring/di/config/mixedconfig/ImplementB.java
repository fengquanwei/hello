package com.fengquanwei.hello.spring.di.config.mixedconfig;

/**
 * ImplementB
 *
 * @author fengquanwei
 * @create 2017/12/26 16:35
 **/
public class ImplementB implements InterfaceB {
    private InterfaceA interfaceA;

    public ImplementB(InterfaceA interfaceA) {
        this.interfaceA = interfaceA;
    }

    @Override
    public void methodB() {
        interfaceA.methodA();
        System.out.println("ImplementB.methodB()");
    }
}
