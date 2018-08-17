package com.fengquanwei.hello.javase.concurrent.synchronize.semaphore;

/**
 * Main
 *
 * @author fengquanwei
 * @create 2018/1/27 23:08
 **/
public class Main {
    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue(3);

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new JobTask(printQueue), "JobTaskThread-" + i);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }
}
