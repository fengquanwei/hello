package com.fengquanwei.hello.spring.di.advanced.qualifier;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * ImplementA1
 *
 * @author fengquanwei
 * @create 2017/12/26 16:05
 **/
@Component
@Qualifier("A1") // 指定限定符，默认的限定符是 bean ID（即不指定 A1 则默认为 implementA1）
public class ImplementA1 implements InterfaceA {
    @Override
    public void methodA() {
        System.out.println("ImplementA1.methodA()");
    }
}
