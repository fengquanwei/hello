package com.fengquanwei.hello.javase.concurrent.thread.threadlocal;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 线程安全的任务
 *
 * @author fengquanwei
 * @create 2018/1/23 23:41
 **/
public class SafeTask implements Runnable {
    private static ThreadLocal<Date> dateHolder = new ThreadLocal<Date>() {
        @Override
        protected Date initialValue() {
            return new Date();
        }
    };

    @Override
    public void run() {
        System.out.printf("[%s] Start.    Date: %s\n", Thread.currentThread().getName(), dateHolder.get());

        try {
            TimeUnit.SECONDS.sleep((int) Math.rint(Math.random() * 10));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("[%s] Finished. Date: %s\n", Thread.currentThread().getName(), dateHolder.get());
    }
}
