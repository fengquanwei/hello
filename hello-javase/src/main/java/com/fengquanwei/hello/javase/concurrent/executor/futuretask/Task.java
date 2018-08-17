package com.fengquanwei.hello.javase.concurrent.executor.futuretask;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 任务
 *
 * @author fengquanwei
 * @create 2018/2/5 11:19
 **/
public class Task implements Callable<String> {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String call() throws Exception {
        int duration = new Random().nextInt(10);
        System.out.printf("[%s] Waiting %d seconds for results\n", Thread.currentThread().getName(), duration);
        TimeUnit.SECONDS.sleep(duration);

        return "Hello " + name;
    }
}
