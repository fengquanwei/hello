package com.fengquanwei.hello.javase.concurrent.synchronize.phaser.advance;

/**
 * Main
 *
 * @author fengquanwei
 * @create 2018/1/31 17:19
 **/
public class Main {
    public static void main(String[] args) {
        MyPhaser myPhaser = new MyPhaser();

        StudentTask[] studentTasks = new StudentTask[5];
        for (int i = 0; i < 5; i++) {
            studentTasks[i] = new StudentTask(myPhaser);
            myPhaser.register();
        }

        Thread[] studentTaskThreads = new Thread[studentTasks.length];
        for (int i = 0; i < studentTasks.length; i++) {
            studentTaskThreads[i] = new Thread(studentTasks[i], "StudentTaskThread-" + i);
            studentTaskThreads[i].start();
        }

        for (int i = 0; i < studentTaskThreads.length; i++) {
            try {
                studentTaskThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("[%s] The phaser has finished: %s\n", Thread.currentThread().getName(), myPhaser.isTerminated());
    }
}
