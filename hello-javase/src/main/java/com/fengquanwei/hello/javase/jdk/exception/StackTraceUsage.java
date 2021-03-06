package com.fengquanwei.hello.javase.jdk.exception;

import java.util.Scanner;

/**
 * 堆栈跟踪用法
 *
 * @author fengquanwei
 * @create 2017/4/6 20:30
 **/
public class StackTraceUsage {
    // 阶乘堆栈信息打印
    public static int factorial(int n) {
        System.out.println("factorial(" + n + "):");
        Throwable throwable = new Throwable();
        StackTraceElement[] stackTraceElements = throwable.getStackTrace();
        for (StackTraceElement stackTraceElement : stackTraceElements) {
            System.out.println(stackTraceElement);
        }
        int r;
        if (n <= 1) {
            r = 1;
        } else {
            r = n * factorial(n - 1);
        }
        System.out.println("return " + r);
        return r;
    }

    /**
     * 测试堆栈跟踪
     */
    public static void testStackTrace(){
        // 打印当前位置堆栈跟踪
        Thread.dumpStack();

        Throwable throwable = new Throwable();
        throwable.printStackTrace(System.out);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter n: ");
        int n = scanner.nextInt();
        factorial(n);

//        testStackTrace();
    }
}
