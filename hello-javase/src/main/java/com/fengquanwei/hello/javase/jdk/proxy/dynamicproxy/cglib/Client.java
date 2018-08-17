package com.fengquanwei.hello.javase.jdk.proxy.dynamicproxy.cglib;

/**
 * 测试 CGLIB
 *
 * @author fengquanwei
 * @create 2017/11/20 16:32
 **/
public class Client {
    public static void main(String[] args) {
        Hello proxy = CGLibProxy.getInstance().getProxy(HellpImpl.class);
        proxy.sayHello();
    }
}