package com.fengquanwei.hello.spring.di.config.autoconfig;

import org.springframework.stereotype.Component;

/**
 * 组件扫描
 *
 * @author fengquanwei
 * @create 2017/12/26 16:05
 **/
@Component // 默认的 Bean ID 是类名首字母小写（implementA）【Option1:1】
//@Component("customBeanId") // 也可以自定义 Bean ID【Option1:2】
public class ImplementA implements InterfaceA {
    @Override
    public void methodA() {
        System.out.println("ImplementA.methodA()");
    }
}
