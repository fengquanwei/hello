package com.fengquanwei.hello.javase.concurrent.executor.invokeall;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 运行多个任务并处理所有结果
 *
 * @author fengquanwei
 * @create 2018/2/1 16:52
 **/
public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            taskList.add(new Task("Task-" + i));
        }

        List<Future<Result>> futureList = null;
        try {
            futureList = executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();

        System.out.printf("[%s] Results:\n", Thread.currentThread().getName());
        for (int i = 0; i < futureList.size(); i++) {
            Future<Result> future = futureList.get(i);
            try {
                Result result = future.get();
                System.out.printf("[%s] Result: %s\n", Thread.currentThread().getName(), result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
