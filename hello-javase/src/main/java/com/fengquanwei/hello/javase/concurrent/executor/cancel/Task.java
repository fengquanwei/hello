package com.fengquanwei.hello.javase.concurrent.executor.cancel;

import java.util.concurrent.Callable;

/**
 * 死循环任务
 *
 * @author fengquanwei
 * @create 2018/2/2 20:58
 **/
public class Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        while (true) {
            System.out.printf("[%s] Test\n", Thread.currentThread().getName());
            Thread.sleep(1000);
        }
    }
}
