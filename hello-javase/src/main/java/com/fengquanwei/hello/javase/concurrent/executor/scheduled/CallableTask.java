package com.fengquanwei.hello.javase.concurrent.executor.scheduled;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * 任务
 *
 * @author fengquanwei
 * @create 2018/2/1 17:48
 **/
public class CallableTask implements Callable<String> {
    private String name;

    public CallableTask(String name) {
        this.name = name;
    }

    @Override
    public String call() throws Exception {
        System.out.printf("[%s] %s start at: %s\n", Thread.currentThread().getName(), this.name, new Date());
        return "Hello " + this.name;
    }
}
