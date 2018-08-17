package com.fengquanwei.hello.javase.concurrent.synchronize.lock.readwritelock;

/**
 * 读写锁
 *
 * @author fengquanwei
 * @create 2018/1/26 20:59
 **/
public class Main {
    public static void main(String[] args) {
        PricesInfo pricesInfo = new PricesInfo();

        ReadTask[] readTasks = new ReadTask[5];
        Thread[] readTaskThreads = new Thread[5];

        for (int i = 0; i < 5; i++) {
            readTasks[i] = new ReadTask(pricesInfo);
            readTaskThreads[i] = new Thread(readTasks[i], "ReadTaskThread-" + i);
        }

        WriteTask writeTask = new WriteTask(pricesInfo);
        Thread writeTaskThread = new Thread(writeTask, "WriteTaskThread");

        for (int i = 0; i < 5; i++) {
            readTaskThreads[i].start();
        }
        writeTaskThread.start();
    }
}
