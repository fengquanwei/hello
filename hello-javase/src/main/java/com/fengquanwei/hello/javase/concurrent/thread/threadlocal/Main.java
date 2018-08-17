package com.fengquanwei.hello.javase.concurrent.thread.threadlocal;

import java.util.concurrent.TimeUnit;

/**
 * 线程局部变量
 *
 * @author fengquanwei
 * @create 2018/1/23 23:32
 **/
public class Main {
    public static void main(String[] args) {
//        UnsafeTask task = new UnsafeTask();
        SafeTask task = new SafeTask();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(task, "TaskThread-" + i);
            thread.start();

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
