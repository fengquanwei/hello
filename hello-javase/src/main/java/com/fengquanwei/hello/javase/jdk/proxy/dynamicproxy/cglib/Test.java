package com.fengquanwei.hello.javase.jdk.proxy.dynamicproxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Test
 *
 * @author fengquanwei
 * @create 2017/12/5 16:39
 **/
public class Test {
    public void say() {
        System.out.println("Hi");
    }

    public static void main(String[] args) {
        Test test = (Test) Enhancer.create(Test.class, new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                String name = method.getName();
                if ("say".equals(name)) {
                    System.out.println("before");
                }

                Object o1 = methodProxy.invokeSuper(o, objects);

                if ("say".equals(name)) {
                    System.out.println("after");
                }
                return o1;
            }
        });

        test.say();
    }
}
