package com.fengquanwei.hello.algorithm.math;

/**
 * 幂运算
 *
 * @author fengquanwei
 * @create 2018/2/10 12:56
 **/
public class Pow {
    /**
     * O(logN)
     */
    public static int pow(int x, int n) {
        if (n == 0) {
            return 1;
        }

        if (n == 1) {
            return x;
        }

        if (n % 2 == 0) {
            return pow(x * x, n / 2);
        } else {
            return pow(x * x, n / 2) * x;
        }
    }

    public static void main(String[] args) {
        System.out.println(pow(2, 11));
    }
}
