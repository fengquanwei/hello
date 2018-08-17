package com.fengquanwei.hello.javase.concurrent.synchronize.cyclicbarrier;

import java.util.Random;

/**
 * 矩阵
 *
 * @author fengquanwei
 * @create 2018/1/28 22:01
 **/
public class Matrix {
    private int data[][];

    // 矩阵的行数，每行的长度，要找的数字
    public Matrix(int size, int length, int number) {
        int counter = 0;
        data = new int[size][length];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j] = random.nextInt(10);
                if (data[i][j] == number) {
                    counter++;
                }
            }
        }

        System.out.printf("[%s] Matrix: There are %d occurrences of %d in generated data.\n", Thread.currentThread().getName(), counter, number);
    }

    public int[] getRow(int row) {
        if ((row >= 0) && (row < data.length)) {
            return data[row];
        }
        return null;
    }
}
