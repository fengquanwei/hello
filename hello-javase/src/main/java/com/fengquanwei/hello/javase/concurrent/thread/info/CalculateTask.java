package com.fengquanwei.hello.javase.concurrent.thread.info;

/**
 * 计算任务
 *
 * @author fengquanwei
 * @create 2018/1/21 19:57
 **/
public class CalculateTask implements Runnable {
    private int number;

    public CalculateTask(int number) {
        this.number = number;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("[%s] %d * %d = %d\n", Thread.currentThread().getName(), number, i, number * i);
        }
    }
}
