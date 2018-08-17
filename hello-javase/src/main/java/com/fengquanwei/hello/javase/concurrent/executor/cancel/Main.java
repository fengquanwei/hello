package com.fengquanwei.hello.javase.concurrent.executor.cancel;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 在执行器中取消任务
 *
 * @author fengquanwei
 * @create 2018/2/5 11:09
 **/
public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        Task task = new Task();

        System.out.printf("[%s] Executing the task\n", Thread.currentThread().getName());
        Future<String> future = executor.submit(task);

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("[%s] Canceling the task\n", Thread.currentThread().getName());
        future.cancel(true);

        System.out.printf("[%s] Cancelled: %s\n", Thread.currentThread().getName(), future.isCancelled());
        System.out.printf("[%s] Done: %s\n", Thread.currentThread().getName(), future.isDone());

        executor.shutdown();
        System.out.printf("[%s] The executor has finished\n", Thread.currentThread().getName());
    }
}
