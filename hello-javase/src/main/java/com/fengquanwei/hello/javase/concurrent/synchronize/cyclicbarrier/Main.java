package com.fengquanwei.hello.javase.concurrent.synchronize.cyclicbarrier;

import java.util.concurrent.CyclicBarrier;

/**
 * 循环障栅
 *
 * @author fengquanwei
 * @create 2018/1/28 22:18
 **/
public class Main {
    public static void main(String[] args) {
        final int SIZE = 10000; // 矩阵行数
        final int LENGTH = 1000; // 每行长度
        final int NUMBER = 5; // 待查数字
        final int PARTICIPANTS = 5; // 参与搜索的线程个数
        final int LINES_PARTICIPANT = 2000; // 每个线程参与搜索的行数

        Matrix matrix = new Matrix(SIZE, LENGTH, NUMBER);
        Result result = new Result(SIZE);
        GroupTask groupTask = new GroupTask(result);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(PARTICIPANTS, groupTask);

        for (int i = 0; i < PARTICIPANTS; i++) {
            SearchTask searchTask = new SearchTask(matrix, i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT) + LINES_PARTICIPANT, result, NUMBER, cyclicBarrier);
            Thread searchTaskThread = new Thread(searchTask, "SearchTaskThread-" + i);
            searchTaskThread.start();
        }

        System.out.printf("[%s] The main thread has finished\n", Thread.currentThread().getName());
    }
}
