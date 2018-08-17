package com.fengquanwei.hello.javase.concurrent.synchronize.synchroized.wait;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 事件存储
 *
 * @author fengquanwei
 * @create 2018/1/26 19:46
 **/
public class EventStorage {
    private int maxSize;
    private List<Date> storage;

    public EventStorage() {
        this.maxSize = 10;
        this.storage = new LinkedList<>();
    }

    public synchronized void set() {
        while (storage.size() == maxSize) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Date date = new Date();
        storage.add(date);
        System.out.printf("[%s] Set. Size: %d. Event: %s\n", Thread.currentThread().getName(), storage.size(), date);
        notifyAll();
    }

    public synchronized void get() {
        while (storage.size() == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("[%s] Get. Size: %d. Event: %s\n", Thread.currentThread().getName(), storage.size(), ((LinkedList) storage).poll());
        notifyAll();
    }

}
