package com.fengquanwei.hello.spring.aop.introduction;

import org.springframework.stereotype.Component;

/**
 * ImplementB
 *
 * @author fengquanwei
 * @create 2018/1/2 15:08
 **/
@Component
public class ImplementB implements InterfaceB {
    @Override
    public void methodB() {
        System.out.println("ImplementB.methodB()");
    }
}
