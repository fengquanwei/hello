package com.fengquanwei.hello.javase.concurrent.synchronize.lock.reentrantlock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 打印队列
 *
 * @author fengquanwei
 * @create 2018/1/26 20:31
 **/
public class PrintQueue {
    private final Lock lock = new ReentrantLock();

    public void printJob(Object document) {
        lock.lock();
        try {
            Long duration = (long) (Math.random() * 10000);
            System.out.printf("[%s] PrintQueue: printing a job(%s) during %s millis\n", Thread.currentThread().getName(), document, duration);
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}
