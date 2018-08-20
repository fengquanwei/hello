package com.fengquanwei.hello.maven;

/**
 * HelloWorld
 *
 * @author fengquanwei
 * @create 2018/8/20 16:35
 **/
public class HelloWorld {
    public String sayHello() {
        return "Hello Maven";
    }

    public static void main(String[] args) {
        System.out.println(new HelloWorld().sayHello());
    }
}
