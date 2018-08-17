package com.fengquanwei.hello.javase.concurrent.executor.completionservice;

import java.util.concurrent.*;

/**
 * 分离任务的启动与结果的处理
 *
 * @author fengquanwei
 * @create 2018/2/5 14:10
 **/
public class Main {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CompletionService<String> completionService = new ExecutorCompletionService<>(executorService);

        ReportRequestTask reportRequestTask1 = new ReportRequestTask("ReportRequest1", completionService);
        Thread reportRequestThread1 = new Thread(reportRequestTask1, "ReportRequestThread-1");

        ReportRequestTask reportRequestTask2 = new ReportRequestTask("ReportRequest2", completionService);
        Thread reportRequestThread2 = new Thread(reportRequestTask2, "ReportRequestThread-2");

        ReportProcessTask reportProcessTask = new ReportProcessTask(completionService);
        Thread reportProcessThread = new Thread(reportProcessTask, "ReportProcessThread");

        System.out.printf("[%s] Starting the threads\n", Thread.currentThread().getName());
        reportRequestThread1.start();
        reportRequestThread2.start();
        reportProcessThread.start();

        System.out.printf("[%s] Waiting for the report generators\n", Thread.currentThread().getName());
        try {
            reportRequestThread1.join();
            reportRequestThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("[%s] Shutting down the executor\n", Thread.currentThread().getName());
        executorService.shutdown();
        try {
            executorService.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        reportProcessTask.setEnd(true);
        System.out.printf("[%s] End\n", Thread.currentThread().getName());
    }
}
