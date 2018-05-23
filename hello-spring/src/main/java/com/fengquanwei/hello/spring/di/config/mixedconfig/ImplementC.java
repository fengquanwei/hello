package com.fengquanwei.hello.spring.di.config.mixedconfig;

/**
 * ImplementC
 *
 * @author fengquanwei
 * @create 2017/12/26 20:44
 **/
public class ImplementC implements InterfaceC {
    private InterfaceA interfaceA;

    public ImplementC(InterfaceA interfaceA) {
        this.interfaceA = interfaceA;
    }

    @Override
    public void methodC() {
        interfaceA.methodA();
        System.out.println("ImplementC.methodC()");
    }
}
