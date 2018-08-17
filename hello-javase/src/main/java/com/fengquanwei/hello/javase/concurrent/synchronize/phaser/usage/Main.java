package com.fengquanwei.hello.javase.concurrent.synchronize.phaser.usage;

import java.util.concurrent.Phaser;

/**
 * 多阶段同步
 *
 * @author fengquanwei
 * @create 2018/1/29 22:44
 **/
public class Main {
    public static void main(String[] args) {
        // 参与阶段同步的线程是 3 个
        Phaser phaser = new Phaser(3);

        FileSearchTask fileSearchTask1 = new FileSearchTask("/opt", "log", phaser);
        FileSearchTask fileSearchTask2 = new FileSearchTask("/Users/fengquanwei/Documents", "pdf", phaser);
        FileSearchTask fileSearchTask3 = new FileSearchTask("/Users/fengquanwei/Pictures", "jpg", phaser);

        Thread fileSearchTaskThread1 = new Thread(fileSearchTask1, "FileSearchTaskThread1");
        fileSearchTaskThread1.start();
        Thread fileSearchTaskThread2 = new Thread(fileSearchTask2, "FileSearchTaskThread2");
        fileSearchTaskThread2.start();
        Thread fileSearchTaskThread3 = new Thread(fileSearchTask3, "FileSearchTaskThread3");
        fileSearchTaskThread3.start();

        try {
            fileSearchTaskThread1.join();
            fileSearchTaskThread2.join();
            fileSearchTaskThread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("[%s] Terminated: %s\n", Thread.currentThread().getName(), phaser.isTerminated());
    }
}
