package com.fengquanwei.hello.javase.concurrent.thread.exception.threadgroup;

import java.util.Random;

/**
 * 任务
 *
 * @author fengquanwei
 * @create 2018/1/24 19:44
 **/
public class Task implements Runnable {
    @Override
    public void run() {
        int result;

        Random random = new Random(Thread.currentThread().getId());
        while (true) {
            result = 1000 / ((int) (random.nextDouble() * 100));
            System.out.printf("[%s] Result: %d\n", Thread.currentThread().getName(), result);
            if (Thread.currentThread().isInterrupted()) {
                System.out.printf("[%s] Interrupted\n", Thread.currentThread().getName());
                return;
            }
        }
    }
}
