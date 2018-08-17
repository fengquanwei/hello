package com.fengquanwei.hello.javase.concurrent.executor.futuretask;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 未来任务
 *
 * @author fengquanwei
 * @create 2018/2/5 11:22
 **/
public class MyFutureTask extends FutureTask<String> {
    private String name;

    public MyFutureTask(Callable<String> callable) {
        super(callable);
        this.name = ((Task) callable).getName();
    }

    @Override
    protected void done() {
        if (isCancelled()) {
            System.out.printf("[%s] %s has been canceled\n", Thread.currentThread().getName(), name);
        } else {
            System.out.printf("[%s] %s has finished\n", Thread.currentThread().getName(), name);
        }
    }
}
