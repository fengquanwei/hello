package com.fengquanwei.hello.javase.concurrent.synchronize.cyclicbarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 查找任务：在随机矩阵中指定的行中查找某个数
 *
 * @author fengquanwei
 * @create 2018/1/28 22:08
 **/
public class SearchTask implements Runnable {
    private Matrix matrix; // 矩阵
    private int firstRow; // 要查找的首行
    private int lastRow; // 要查找的尾行
    private Result result; // 查找结果
    private int number; // 要查找的数字
    private final CyclicBarrier cyclicBarrier;

    public SearchTask(Matrix matrix, int firstRow, int lastRow, Result result, int number, CyclicBarrier cyclicBarrier) {
        this.matrix = matrix;
        this.firstRow = firstRow;
        this.lastRow = lastRow;
        this.result = result;
        this.number = number;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        int count;
        System.out.printf("[%s] Processing lines from %d to %d\n", Thread.currentThread().getName(), firstRow, lastRow);
        for (int i = firstRow; i < lastRow; i++) {
            int[] row = matrix.getRow(i);
            count = 0;
            for (int j = 0; j < row.length; j++) {
                if (row[j] == number) {
                    count++;
                }
            }
            result.setData(i, count);
        }
        System.out.printf("[%s] Lines processed\n", Thread.currentThread().getName());

        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }
}
