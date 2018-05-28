package com.fengquanwei.hello.spring.di.advanced.spel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ImplementB
 *
 * @author fengquanwei
 * @create 2018/5/28 15:47
 **/
@Component
public class ImplementB implements InterfaceB {
    private String name;
    private String hello;
    private String userDir;

    @Autowired
    public ImplementB(@Value("#{implementA.name ?: 'Default'}") String name, // 引用 bean 的属性（?: 用于NPE 判断）
                      @Value("#{implementA.sayHello()?.toUpperCase()}") String hello, // 调用 bean 的方法（? 用于NPE 判断）
                      @Value("#{systemProperties['user.dir']}") String userDir) { // 引用系统属性
        this.name = name;
        this.hello = hello;
        this.userDir = userDir;
    }

    @Override
    public void methodB() {
        System.out.println("ImplementB.methodB() this: " + this);
    }

    @Override
    public String toString() {
        return "ImplementB{" +
                "name='" + name + '\'' +
                ", hello='" + hello + '\'' +
                ", userDir='" + userDir + '\'' +
                '}';
    }
}
