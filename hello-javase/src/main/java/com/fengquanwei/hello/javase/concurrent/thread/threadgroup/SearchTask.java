package com.fengquanwei.hello.javase.concurrent.thread.threadgroup;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 搜索任务
 *
 * @author fengquanwei
 * @create 2018/1/23 23:51
 **/
public class SearchTask implements Runnable {
    private Result result;

    public SearchTask(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        System.out.printf("[%s] Start\n", name);

        try {
            doTask();
            result.setName(name);
        } catch (InterruptedException e) {
            System.out.printf("[%s] Interrupted\n", name);
            return;
        }

        System.out.printf("[%s] End\n", name);

    }

    private void doTask() throws InterruptedException {
        Random random = new Random(new Date().getTime());
        int value = (int) (random.nextDouble() * 100);

        System.out.printf("[%s] Sleep: %d\n", Thread.currentThread().getName(), value);
        TimeUnit.SECONDS.sleep(value);
    }
}
