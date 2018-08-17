package com.fengquanwei.hello.javase.concurrent.thread.threadgroup;

import java.util.concurrent.TimeUnit;

/**
 * 线程组
 *
 * @author fengquanwei
 * @create 2018/1/23 23:58
 **/
public class Main {
    public static void main(String[] args) {
        ThreadGroup threadGroup = new ThreadGroup("SearchTaskGroup");

        Result result = new Result();
        SearchTask searchTask = new SearchTask(result);

        for (int i = 0; i < 5; i++) {
            Thread searchTaskThread = new Thread(threadGroup, searchTask, "SearchTaskThread-" + i);
            searchTaskThread.start();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.printf("[%s] Number of threads: %d\n", Thread.currentThread().getName(), threadGroup.activeCount());
        System.out.printf("[%s] Information about the thread group\n", Thread.currentThread().getName());
        threadGroup.list();

        Thread[] threads = new Thread[threadGroup.activeCount()];
        threadGroup.enumerate(threads);
        for (int i = 0; i < threadGroup.activeCount(); i++) {
            System.out.printf("[%s] %s state: %s\n", Thread.currentThread().getName(), threads[i].getName(), threads[i].getState());
        }

        waitFinish(threadGroup);

        threadGroup.interrupt();
    }

    private static void waitFinish(ThreadGroup threadGroup) {
        while (threadGroup.activeCount() > 9) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
