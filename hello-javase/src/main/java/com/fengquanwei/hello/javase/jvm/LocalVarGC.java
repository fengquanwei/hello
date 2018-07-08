package com.fengquanwei.hello.javase.jvm;

/**
 * 局部变量表对 GC 的影响
 * -XX:+PrintGC
 *
 * @author fengquanwei
 * @create 2018/7/8 01:02
 **/
public class LocalVarGC {
    public void localVarGC1() {
        byte[] bytes = new byte[6 * 1024 * 1024];
        System.gc(); // 数组被引用，数组不可回收
    }

    public void localVarGC2() {
        byte[] bytes = new byte[6 * 1024 * 1024];
        bytes = null;
        System.gc(); // 数组失去引用，数组可以回收
    }

    public void localVarGC3() {
        {
            byte[] bytes = new byte[6 * 1024 * 1024];
        }
        System.gc(); // 虽然 bytes 离开了作用域，但是依旧在局部变量表中，数组不可回收
    }

    public void localVarGC4() {
        {
            byte[] bytes = new byte[6 * 1024 * 1024];
        }
        int c = 10;
        System.gc(); // c 复用了 bytes 的槽位，bytes 被销毁，数组可以回收。
    }

    public void localVarGC5() {
        localVarGC1();
        System.gc(); // localVarGC1 函数的栈帧被销毁，数组可以回收
    }

    public static void main(String[] args) {
        LocalVarGC localVarGC = new LocalVarGC();

        System.out.println("==================== 1 ====================");
        localVarGC.localVarGC1();

        System.out.println("==================== 2 ====================");
        localVarGC.localVarGC2();

        System.out.println("==================== 3 ====================");
        localVarGC.localVarGC3();

        System.out.println("==================== 4 ====================");
        localVarGC.localVarGC4();

        System.out.println("==================== 5 ====================");
        localVarGC.localVarGC5();
    }
}
