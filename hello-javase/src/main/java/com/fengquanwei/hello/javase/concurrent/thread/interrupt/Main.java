package com.fengquanwei.hello.javase.concurrent.thread.interrupt;

import java.util.concurrent.TimeUnit;

/**
 * 中断线程
 *
 * @author fengquanwei
 * @create 2018/1/21 21:21
 **/
public class Main {
    public static void main(String[] args) {
        // 执行质数生成任务
        PrimeGenerateTask primeGenerateTask = new PrimeGenerateTask();
        Thread primeGenerateTaskThread = new Thread(primeGenerateTask, "PrimeGenerateTaskThread");
        primeGenerateTaskThread.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        primeGenerateTaskThread.interrupt();

        // 执行文件搜索任务
        FileSearchTask fileSearchTask = new FileSearchTask("/Users", "a.txt");
        Thread fileSearchTaskThread = new Thread(fileSearchTask, "FileSearchTaskThread");
        fileSearchTaskThread.start();

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fileSearchTaskThread.interrupt();
    }
}
