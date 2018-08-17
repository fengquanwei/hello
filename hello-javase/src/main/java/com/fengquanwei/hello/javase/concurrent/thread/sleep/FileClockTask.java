package com.fengquanwei.hello.javase.concurrent.thread.sleep;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 文件时钟任务
 *
 * @author fengquanwei
 * @create 2018/1/23 20:21
 **/
public class FileClockTask implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("[%s] Date: %s\n", Thread.currentThread().getName(), new Date());
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                System.out.printf("[%s] Interrupted\n", Thread.currentThread().getName());
            }
        }
    }
}
