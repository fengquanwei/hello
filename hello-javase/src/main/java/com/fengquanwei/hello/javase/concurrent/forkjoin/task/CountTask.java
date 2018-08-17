package com.fengquanwei.hello.javase.concurrent.forkjoin.task;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * 计算任务
 *
 * @author fengquanwei
 * @create 2018/3/3 17:12
 **/
public class CountTask extends RecursiveTask<Long> {
    private static final int THRESHOLD = 10000;
    private long start;
    private long end;

    public CountTask(long start, long end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Long compute() {
        long sum = 0;

        boolean canCompute = (end - start) < THRESHOLD;

        if (canCompute) {
            for (long i = start; i < end; i++) {
                sum += i;
            }
        } else {
            // 分成 100 个小任务
            long step = (start + end) / 100;
            ArrayList<CountTask> subTasks = new ArrayList<>();
            long pos = start;
            for (int i = 0; i < 100; i++) {
                long lastOne = pos + step;
                if (lastOne > end) {
                    lastOne = end;
                }

                CountTask subTask = new CountTask(pos, lastOne);
                subTasks.add(subTask);

                pos += step + 1;

                subTask.fork();
            }

            for (CountTask task : subTasks) {
                sum += task.join();
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        CountTask task = new CountTask(0, 20000);
        ForkJoinTask<Long> forkJoinTask = forkJoinPool.submit(task);

        try {
            Long result = forkJoinTask.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
