package com.fengquanwei.hello.spring.aop.xmlconfig;

/**
 * ImplementC
 *
 * @author fengquanwei
 * @create 2017/12/26 16:05
 **/
public class ImplementC implements InterfaceC {
    @Override
    public String methodC(String param) {
        System.out.println("ImplementC.methodC(" + param + ")");
        return "ResultC";
    }
}
