package com.fengquanwei.hello.javase.concurrent.thread.exception.threadgroup;

/**
 * 使用自定义未捕获异常处理器处理线程组的未捕获异常
 *
 * @author fengquanwei
 * @create 2018/1/24 19:46
 **/
public class Main {
    public static void main(String[] args) {
        MyThreadGroup myThreadGroup = new MyThreadGroup("MyThreadGroup");
        Task task = new Task();

        for (int i = 0; i < 10; i++) {
            Thread taskThread = new Thread(myThreadGroup, task, "TaskThread");
            taskThread.start();
        }
    }
}
