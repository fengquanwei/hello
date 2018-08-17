package com.fengquanwei.hello.javase.concurrent.synchronize.exchanger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 数据交换
 *
 * @author fengquanwei
 * @create 2018/1/31 17:43
 **/
public class Main {
    public static void main(String[] args) {
        List<String> buffer1 = new ArrayList<>();
        List<String> buffer2 = new ArrayList<>();

        Exchanger<List<String>> exchanger = new Exchanger<>();

        ProducerTask producerTask = new ProducerTask(buffer1, exchanger);
        ConsumerTask consumerTask = new ConsumerTask(buffer2, exchanger);

        Thread producerTaskThread = new Thread(producerTask, "ProducerTaskThread");
        Thread consumerTaskThread = new Thread(consumerTask, "ConsumerTaskThread");

        producerTaskThread.start();
        consumerTaskThread.start();
    }
}
