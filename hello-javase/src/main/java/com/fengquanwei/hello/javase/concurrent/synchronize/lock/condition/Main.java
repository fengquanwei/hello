package com.fengquanwei.hello.javase.concurrent.synchronize.lock.condition;

/**
 * 在锁中使用多条件
 *
 * @author fengquanwei
 * @create 2018/1/27 14:15
 **/
public class Main {
    public static void main(String[] args) {
        FileMock fileMock = new FileMock(100, 10);
        Buffer buffer = new Buffer(20);

        ProducerTask producerTask = new ProducerTask(fileMock, buffer);
        Thread producerTaskThread = new Thread(producerTask, "ProducerTaskThread");

        ConsumerTask[] consumerTasks = new ConsumerTask[3];
        Thread[] consumerTaskThreads = new Thread[3];

        for (int i = 0; i < 3; i++) {
            consumerTasks[i] = new ConsumerTask(buffer);
            consumerTaskThreads[i] = new Thread(consumerTasks[i], "ConsumerTaskThread-" + i);
        }

        producerTaskThread.start();
        for (int i = 0; i < 3; i++) {
            consumerTaskThreads[i].start();
        }
    }
}
