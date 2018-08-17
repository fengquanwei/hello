package com.fengquanwei.hello.javase.jdk.java8;

/**
 * 默认方法
 *
 * @author fengquanwei
 * @create 2018/2/28 20:49
 **/
public class DefaultMethod {
    // 接口定义
    interface A {
        // 默认方法
        default void m1() {
            System.out.println("A.m1()");
        }

        // 静态方法
        static void m2() {
            System.out.println("A.m2()");
        }
    }

    interface B {
        // 同名默认方法
        default void m1() {
            System.out.println("B.m1()");
        }
    }

    static class C implements A, B {
        // 重写冲突的方法
        @Override
        public void m1() {
            A.super.m1();
            B.super.m1();
            A.m2();
            System.out.println("C.m1()");
        }
    }

    public static void main(String[] args) {
        C c = new C();
        c.m1();
    }
}
