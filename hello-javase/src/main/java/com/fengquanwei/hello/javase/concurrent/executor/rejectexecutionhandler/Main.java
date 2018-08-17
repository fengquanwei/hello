package com.fengquanwei.hello.javase.concurrent.executor.rejectexecutionhandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 处理在执行器中被拒绝的任务
 *
 * @author fengquanwei
 * @create 2018/2/5 14:30
 **/
public class Main {
    public static void main(String[] args) {
        MyRejectExecutionHandler myRejectExecutionHandler = new MyRejectExecutionHandler();
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor.setRejectedExecutionHandler(myRejectExecutionHandler);

        System.out.printf("[%s] Start\n", Thread.currentThread().getName());
        for (int i = 0; i < 3; i++) {
            Task task = new Task("Task-" + i);
            executor.submit(task);
        }

        System.out.printf("[%s] Shutting down the executor\n", Thread.currentThread().getName());
        executor.shutdown();

        System.out.printf("[%s] Sending another task\n", Thread.currentThread().getName());
        Task task = new Task("Task-rejected");
        executor.submit(task);

        System.out.printf("[%s] End\n", Thread.currentThread().getName());
    }
}
