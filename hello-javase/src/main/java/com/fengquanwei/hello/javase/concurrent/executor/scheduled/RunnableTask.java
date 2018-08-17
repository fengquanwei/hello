package com.fengquanwei.hello.javase.concurrent.executor.scheduled;

import java.util.Date;

/**
 * 任务
 *
 * @author fengquanwei
 * @create 2018/2/1 17:48
 **/
public class RunnableTask implements Runnable {
    private String name;

    public RunnableTask(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("[%s] %s start at: %s\n", Thread.currentThread().getName(), this.name, new Date());
    }
}
