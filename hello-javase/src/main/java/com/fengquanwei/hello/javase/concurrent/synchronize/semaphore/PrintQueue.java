package com.fengquanwei.hello.javase.concurrent.synchronize.semaphore;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 打印队列
 *
 * @author fengquanwei
 * @create 2018/1/27 23:03
 **/
public class PrintQueue {
    // 打印机的状态：空闲或正在打印
    private boolean freePrinters[];

    // 信号量：用以保护对打印机的访问
    private final Semaphore semaphore;
    // 保护对 freePrinters 的访问
    private Lock lock;

    public PrintQueue(int count) {
        freePrinters = new boolean[count];
        for (int i = 0; i < count; i++) {
            freePrinters[i] = true;
        }
        this.semaphore = new Semaphore(count);
        lock = new ReentrantLock();
    }

    // 模拟文档打印
    public void printJob(Object document) {
        try {
            // 获取信号量
            semaphore.acquire();

            // 获得分配打印工作的打印机编号
            int assignedPrinter = getPrinter();

            // 模拟文档打印
            long duration = (long) (Math.random() * 10);
            System.out.printf("[%s] Printing a job(%s) in printer %d during %d seconds\n", Thread.currentThread().getName(), document, assignedPrinter, duration);
            TimeUnit.SECONDS.sleep(duration);

            // 打印完毕，标记打印机为空闲
            freePrinters[assignedPrinter] = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 释放信号量
            semaphore.release();
        }
    }

    private int getPrinter() {
        int result = -1;

        try {
            lock.lock();

            for (int i = 0; i < freePrinters.length; i++) {
                if (freePrinters[i]) {
                    result = i;
                    freePrinters[i] = false;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return result;
    }
}
