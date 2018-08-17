package com.fengquanwei.hello.javase.concurrent.thread.sleep;

import java.util.concurrent.TimeUnit;

/**
 * 线程休眠
 *
 * @author fengquanwei
 * @create 2018/1/23 20:23
 **/
public class Main {
    public static void main(String[] args) {
        FileClockTask fileClockTask = new FileClockTask();
        Thread fileClockTaskThread = new Thread(fileClockTask, "FileClockTaskThread");
        fileClockTaskThread.start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        fileClockTaskThread.interrupt();
    }
}
