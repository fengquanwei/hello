package com.fengquanwei.hello.javase.concurrent.executor.callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 执行任务并返回结果
 *
 * @author fengquanwei
 * @create 2018/2/1 15:33
 **/
public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(2);

        List<Future<Integer>> futureList = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Integer number = random.nextInt(10);
            FactorialCalculatorTask factorialCalculatorTask = new FactorialCalculatorTask(number);
            Future<Integer> future = executor.submit(factorialCalculatorTask);
            futureList.add(future);
        }

        do {
            System.out.printf("[%s] Complete tasks: %d\n", Thread.currentThread().getName(), executor.getCompletedTaskCount());
            for (int i = 0; i < futureList.size(); i++) {
                Future<Integer> future = futureList.get(i);
                System.out.printf("[%s] Task %d isDone: %s\n", Thread.currentThread().getName(), i, future.isDone());
            }

            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (executor.getCompletedTaskCount() < futureList.size());

        System.out.printf("[%s] Results:\n", Thread.currentThread().getName());
        for (int i = 0; i < futureList.size(); i++) {
            Future<Integer> future = futureList.get(i);
            Integer number = null;

            try {
                number = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            System.out.printf("[%s] Task %d result: %d\n", Thread.currentThread().getName(), i, number);
        }

        executor.shutdown();
    }
}
