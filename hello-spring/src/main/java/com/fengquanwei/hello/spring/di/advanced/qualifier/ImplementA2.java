package com.fengquanwei.hello.spring.di.advanced.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * ImplementA2
 *
 * @author fengquanwei
 * @create 2017/12/26 16:05
 **/
@Component
@Qualifier("A2") // 指定限定符
public class ImplementA2 implements InterfaceA {
    @Override
    public void methodA() {
        System.out.println("ImplementA2.methodA()");
    }
}
