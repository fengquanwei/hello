package com.fengquanwei.hello.spring.rpc.rmi;

/**
 * ImplementA
 *
 * @author fengquanwei
 * @create 2018/7/24 11:06
 **/
public class ImplementA implements InterfaceA {
    @Override
    public String methodA(String name) {
        return "Hello, " + name;
    }
}
