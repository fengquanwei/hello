package com.fengquanwei.hello.javase.concurrent.thread.exception.thread;

/**
 * 自定义未捕获异常处理器
 *
 * @author fengquanwei
 * @create 2018/1/23 23:05
 **/
public class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("[%s] An exception has been captured\n", t.getName());
        System.out.printf("[%s] Exception: %s: %s\n", t.getName(), e.getClass().getName(), e.getMessage());
        System.out.printf("[%s] Stack trace: \n", t.getName());
        e.printStackTrace(System.out);
        System.out.printf("[%s] Thread status: %s\n", t.getName(), t.getState());
    }
}
