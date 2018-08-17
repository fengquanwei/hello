package com.fengquanwei.hello.javase.concurrent.executor.scheduled;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 延时执行任务 & 周期性执行任务
 *
 * @author fengquanwei
 * @create 2018/2/1 17:31
 **/
public class Main {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor) Executors.newScheduledThreadPool(1);

        System.out.printf("[%s] Start at: %s\n", Thread.currentThread().getName(), new Date());

        // 测试延时执行任务
//        testSchedule(executor);
        // 测试周期性执行任务
        testScheduleAtFixedRate(executor);

        executor.shutdown();

        // 等待任务结束
        try {
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("[%s] End st: %s\n", Thread.currentThread().getName(), new Date());
    }

    /**
     * 测试延时执行任务
     */
    private static void testSchedule(ScheduledThreadPoolExecutor executor) {
        for (int i = 0; i < 5; i++) {
            CallableTask callableTask = new CallableTask("CallableTask-" + i);
            executor.schedule(callableTask, i + 1, TimeUnit.SECONDS);
        }
    }

    /**
     * 测试周期性执行任务
     */
    private static void testScheduleAtFixedRate(ScheduledThreadPoolExecutor executor) {
        RunnableTask task = new RunnableTask("Task");
        ScheduledFuture<?> scheduledFuture = executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

        for (int i = 0; i < 10; i++) {
            System.out.printf("[%s] Delay: %d\n", Thread.currentThread().getName(), scheduledFuture.getDelay(TimeUnit.MILLISECONDS));
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
