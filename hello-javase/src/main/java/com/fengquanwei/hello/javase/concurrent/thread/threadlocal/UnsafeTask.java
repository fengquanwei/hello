package com.fengquanwei.hello.javase.concurrent.thread.threadlocal;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 线程不安全的任务
 *
 * @author fengquanwei
 * @create 2018/1/23 23:29
 **/
public class UnsafeTask implements Runnable {
    private Date date;

    @Override
    public void run() {
        date = new Date();
        System.out.printf("[%s] Start.    Date: %s\n", Thread.currentThread().getName(), date);

        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("[%s] Finished. Date: %s\n", Thread.currentThread().getName(), date);
    }
}
