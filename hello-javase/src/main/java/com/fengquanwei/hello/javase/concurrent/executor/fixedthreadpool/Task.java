package com.fengquanwei.hello.javase.concurrent.executor.fixedthreadpool;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 任务
 *
 * @author fengquanwei
 * @create 2018/1/31 19:54
 **/
public class Task implements Runnable {
    private String name; // 任务名称
    private Date date; // 创建时间

    public Task(String name) {
        this.name = name;
        this.date = new Date();
    }

    @Override
    public void run() {
        System.out.printf("[%s] Task start. Task name: %s, Created on: %s, Started on: %s\n", Thread.currentThread().getName(), name, date, new Date());

        try {
            int duration = new Random().nextInt(10);
            System.out.printf("[%s] Task running. Task name: %s, Duration: %d seconds\n", Thread.currentThread().getName(), name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("[%s] Task end. Task name: %s, Finished on: %s\n", Thread.currentThread().getName(), name, new Date());
    }

    public String getName() {
        return name;
    }
}
