package com.fengquanwei.hello.spring.security.method.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

/**
 * ServiceA
 *
 * @author fengquanwei
 * @create 2018/7/13 15:58
 **/
@Service
public class ServiceA {
    @Secured("ROLE_User")
    public void methodA() {
        System.out.println("ServiceA.methodA()");
    }
}
