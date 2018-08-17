package com.fengquanwei.hello.javase.concurrent.synchronize.lock.reentrantlock;

import java.util.Date;

/**
 * 作业任务
 *
 * @author fengquanwei
 * @create 2018/1/26 20:35
 **/
public class JobTask implements Runnable {
    private PrintQueue printQueue;

    public JobTask(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.printf("[%s] Print a document start\n", Thread.currentThread().getName());
        printQueue.printJob(new Date().getTime());
        System.out.printf("[%s] Print a document end\n", Thread.currentThread().getName());
    }
}
