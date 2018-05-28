package com.fengquanwei.hello.spring.di.advanced.conditional;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

/**
 * ImplementA
 *
 * @author fengquanwei
 * @create 2017/12/26 16:05
 **/
@Component
@Conditional(MyCondition.class) // 条件化的 bean
public class ImplementA implements InterfaceA {
    @Override
    public void methodA() {
        System.out.println("ImplementA.methodA()");
    }
}
