package com.fengquanwei.hello.spring.di.advanced.placeholder.autoconfig;

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

    @Autowired
    public ImplementA(@Value("${name}") String name) {
        this.name = name;
    }

    @Override
    public void methodA() {
        System.out.println("ImplementAmethodA() name: " + this.name);
    }
}
