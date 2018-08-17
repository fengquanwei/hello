package com.fengquanwei.hello.javase.concurrent.thread.threadfactory;

/**
 * Main
 *
 * @author fengquanwei
 * @create 2018/1/24 20:10
 **/
public class Main {
    public static void main(String[] args) {
        MyThreadFactory myThreadFactory = new MyThreadFactory("MyThreadFactory");
        Task task = new Task();

        Thread thread;
        System.out.printf("[%s] Starting the threads\n", Thread.currentThread().getName());
        for (int i = 0; i < 10; i++) {
            thread = myThreadFactory.newThread(task);
            thread.start();
        }

        System.out.printf("[%s] Factory stats:\n%s", Thread.currentThread().getName(), myThreadFactory.getStats());
    }
}
