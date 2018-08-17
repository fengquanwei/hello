package com.fengquanwei.hello.javase.concurrent.thread.exception.thread;

/**
 * 使用自定义未捕获异常处理器处理线程的未捕获异常
 *
 * @author fengquanwei
 * @create 2018/1/23 23:09
 **/
public class Main {
    public static void main(String[] args) {
        Task task = new Task();
        Thread taskThread = new Thread(task, "TaskThread");
        taskThread.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        taskThread.start();
    }
}
