package com.fengquanwei.hello.spring.di.advanced.primary;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * ImplementA1
 *
 * @author fengquanwei
 * @create 2017/12/26 16:05
 **/
@Component
@Primary // 首选 Bean
public class ImplementA1 implements InterfaceA {
    @Override
    public void methodA() {
        System.out.println("ImplementA1.methodA()");
    }
}
