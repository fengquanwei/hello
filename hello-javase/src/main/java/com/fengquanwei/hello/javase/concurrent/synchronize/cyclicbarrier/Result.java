package com.fengquanwei.hello.javase.concurrent.synchronize.cyclicbarrier;

/**
 * 结果：矩阵中每行找到指定数字的次数
 *
 * @author fengquanwei
 * @create 2018/1/28 22:06
 **/
public class Result {
    private int data[];

    public Result(int size) {
        this.data = new int[size];
    }

    public int[] getData() {
        return data;
    }

    public void setData(int position, int value) {
        data[position] = value;
    }
}
