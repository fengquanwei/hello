package com.fengquanwei.hello.javase.concurrent.synchronize.lock.reentrantlock;

/**
 * 可重入锁
 *
 * @author fengquanwei
 * @create 2018/1/26 20:37
 **/
public class Main {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new JobTask(printQueue), "JobTaskThread-" + i);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
