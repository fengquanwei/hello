package com.fengquanwei.hello.javase.concurrent.executor.futuretask;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 未来任务测试
 *
 * @author fengquanwei
 * @create 2018/2/5 11:24
 **/
public class Main {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();

        MyFutureTask[] myFutureTasks = new MyFutureTask[5];
        for (int i = 0; i < 5; i++) {
            Task task = new Task("Task-" + i);
            myFutureTasks[i] = new MyFutureTask(task);
            executor.submit(myFutureTasks[i]);
        }

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < myFutureTasks.length; i++) {
            myFutureTasks[i].cancel(true);
        }

        for (int i = 0; i < myFutureTasks.length; i++) {
            try {
                if (!myFutureTasks[i].isCancelled()) {
                    System.out.printf("[%s] Result: %s\n", Thread.currentThread().getName(), myFutureTasks[i].get());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
    }
}
