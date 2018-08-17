package com.fengquanwei.hello.javase.concurrent.thread.exception.thread;

/**
 * 任务
 *
 * @author fengquanwei
 * @create 2018/1/23 23:08
 **/
public class Task implements Runnable {
    @Override
    public void run() {
        // 抛出一个运行时异常
        int error = Integer.parseInt("Error");
    }
}
