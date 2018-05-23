package com.fengquanwei.hello.spring.di.config.xmlconfig;

import java.util.List;

/**
 * ImplementB
 *
 * @author fengquanwei
 * @create 2017/12/26 16:35
 **/
public class ImplementB implements InterfaceB {
    private InterfaceA interfaceA;
    private String string;
    private List<String> list;
    private Integer integer;

    // 强依赖使用构造器注入
    public ImplementB(InterfaceA interfaceA, String string, List<String> list) {
        this.interfaceA = interfaceA;
        this.string = string;
        this.list = list;
    }

    // 可选依赖使用属性注入
    public void setInteger(Integer integer) {
        this.integer = integer;
    }

    @Override
    public void methodB() {
        interfaceA.methodA();
        System.out.println("ImplementB.methodB()");
        System.out.println("this.interfaceA: " + this.interfaceA);
        System.out.println("this.string: " + this.string);
        System.out.println("this.list: " + this.list);
        System.out.println("this.integer: " + this.integer);
    }


}
