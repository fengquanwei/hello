package com.fengquanwei.hello.spring.di.advanced.spel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ImplementA
 *
 * @author fengquanwei
 * @create 2017/12/26 16:05
 **/
@Component
public class ImplementA implements InterfaceA {
    private String name;
    private Long millis;
    private double pi;
    private int product;
    private boolean match;

    @Autowired
    public ImplementA(@Value("#{'Lask' [0]}") String name, // [] 运算符：按索引获取元素
                      @Value("#{T(System).currentTimeMillis()}") long millis, // T 运算符：用于访问类变量和方法
                      @Value("#{3.141592654E0}") double pi, // 字面量
                      @Value("#{2 * T(java.lang.Math).PI}") int product,// 运算符
                      @Value("#{'abc@163.com' matches '[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.com'}") boolean match) { // 正则表达式

        this.name = name;
        this.millis = millis;
        this.pi = pi;
        this.product = product;
        this.match = match;
    }

    @Override
    public void methodA() {
        System.out.println("ImplementA.methodA() this: " + this);
    }

    public String getName() {
        return name;
    }

    public String sayHello() {
        return "Hello World";
    }

    @Override
    public String toString() {
        return "ImplementA{" +
                "name='" + name + '\'' +
                ", millis=" + millis +
                ", pi=" + pi +
                ", product=" + product +
                ", match=" + match +
                '}';
    }
}
