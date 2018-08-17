package com.fengquanwei.hello.javase.concurrent.synchronize.cyclicbarrier;

/**
 * 计算在矩阵中查找到的总次数
 *
 * @author fengquanwei
 * @create 2018/1/28 22:16
 **/
public class GroupTask implements Runnable {
    private Result result;

    public GroupTask(Result result) {
        this.result = result;
    }

    @Override
    public void run() {
        int finalResult = 0;
        System.out.printf("[%s] Processing results start\n", Thread.currentThread().getName());
        int[] data = result.getData();
        for (int number : data) {
            finalResult += number;
        }
        System.out.printf("[%s] Processing results end. Total result: %d\n", Thread.currentThread().getName(), finalResult);
    }
}
