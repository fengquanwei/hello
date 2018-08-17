package com.fengquanwei.hello.javase.concurrent.executor.rejectexecutionhandler;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * 任务
 *
 * @author fengquanwei
 * @create 2018/2/5 14:34
 **/
public class Task implements Runnable {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.printf("[%s] %s starting\n", Thread.currentThread().getName(), this.name);
        int duration = new Random().nextInt(10);
        System.out.printf("[%s] Waiting %d seconds for task\n", Thread.currentThread().getName(), duration);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.printf("[%s] %s end\n", Thread.currentThread().getName(), this.name);
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                '}';
    }
}
