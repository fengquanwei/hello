package com.fengquanwei.hello.algorithm.math;

/**
 * 最大公约数
 *
 * @author fengquanwei
 * @create 2018/2/10 12:49
 **/
public class GreatestCommonDivisor {
    /**
     * 欧几里得算法求最大公约数
     * O(logN)
     */
    public static int gcd(int m, int n) {
        if (m < n) {
            int t = m;
            m = n;
            n = t;
        }

        while (n != 0) {
            int t = m % n;
            m = n;
            n = t;
        }

        return m;
    }

    public static void main(String[] args) {
        System.out.println(gcd(24, 56));
    }
}
