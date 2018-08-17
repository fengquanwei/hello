package com.fengquanwei.hello.javase.concurrent.synchronize.synchroized.wait;

/**
 * 使用条件（生产者与消费者）
 *
 * @author fengquanwei
 * @create 2018/1/26 19:55
 **/
public class Main {
    public static void main(String[] args) {
        EventStorage eventStorage = new EventStorage();

        ProducerTask producerTask = new ProducerTask(eventStorage);
        Thread producerTaskThread = new Thread(producerTask, "ProducerTaskThread");

        ConsumerTask consumerTask = new ConsumerTask(eventStorage);
        Thread consumerTaskThread = new Thread(consumerTask, "ConsumerTaskThread");

        producerTaskThread.start();
        consumerTaskThread.start();
    }
}
