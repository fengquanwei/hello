package com.fengquanwei.hello.javase.concurrent.executor.invokeall;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 任务
 *
 * @author fengquanwei
 * @create 2018/2/1 16:47
 **/
public class Task implements Callable<Result> {
    private String name;

    public Task(String name) {
        this.name = name;
    }

    @Override
    public Result call() throws Exception {
        System.out.printf("[%s] %s start\n", Thread.currentThread().getName(), this.name);

        Random random = new Random();
        try {
            int duration = random.nextInt(10);
            System.out.printf("[%s] %s waiting %d seconds for results\n", Thread.currentThread().getName(), this.name, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Result result = new Result();
        result.setName(this.name);
        result.setValue(random.nextInt(10000));

        System.out.printf("[%s] %s end. Result: %s\n", Thread.currentThread().getName(), this.name, result);
        return result;
    }
}
