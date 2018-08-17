package com.fengquanwei.hello.javase.concurrent.synchronize.semaphore;

import java.util.Date;

/**
 * 工作任务
 *
 * @author fengquanwei
 * @create 2018/1/27 23:06
 **/
public class JobTask implements Runnable {
    private PrintQueue printQueue;

    public JobTask(PrintQueue printQueue) {
        this.printQueue = printQueue;
    }

    @Override
    public void run() {
        System.out.printf("[%s] Print a job start\n", Thread.currentThread().getName());
        printQueue.printJob(new Date().getTime());
        System.out.printf("[%s] Print a job end\n", Thread.currentThread().getName());
    }
}
