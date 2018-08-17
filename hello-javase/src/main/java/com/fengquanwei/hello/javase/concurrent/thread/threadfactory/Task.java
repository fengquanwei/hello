package com.fengquanwei.hello.javase.concurrent.thread.threadfactory;

import java.util.concurrent.TimeUnit;

/**
 * 任务
 *
 * @author fengquanwei
 * @create 2018/1/24 20:10
 **/
public class Task implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
