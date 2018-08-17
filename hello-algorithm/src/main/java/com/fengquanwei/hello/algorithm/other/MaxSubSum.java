package com.fengquanwei.hello.algorithm.other;

/**
 * 最大子序列和
 *
 * @author fengquanwei
 * @create 2018/2/9 23:19
 **/
public class MaxSubSum {
    /**
     * 方法一：穷举
     * 穷举 a[i]-a[j] 的和（i <= j）
     * O(N^3)
     */
    public static int maxSubSum1(int[] a) {
        int maxSum = 0;

        for (int i = 0; i < a.length; i++) {
            for (int j = i; j < a.length; j++) {
                int thisSum = 0;
                for (int k = i; k <= j; k++) {
                    thisSum += a[k];
                }

                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }

        return maxSum;
    }

    /**
     * 方法二：穷举优化
     * 消灭重复计算
     * O(N^2)
     */
    public static int maxSubSum2(int[] a) {
        int maxSum = 0;

        for (int i = 0; i < a.length; i++) {
            int thisSum = 0;
            for (int j = i; j < a.length; j++) {
                thisSum += a[j];
                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }

        return maxSum;
    }

    /**
     * 方法三：分治
     * max(左半部分最大和, 右半部分最大和, 跨边界部分最大和)
     * 跨边界部分最大和 = 包括最后一个元素在内左半部分最大和 + 包括第一个元素在内右半部分最大和
     * O(N*logN)
     */
    public static int maxSubSum3(int[] a) {
        return maxSubRec(a, 0, a.length - 1);
    }

    private static int maxSubRec(int[] a, int left, int right) {
        if (left == right) {
            return a[left] > 0 ? a[left] : 0;
        }

        int center = (left + right) / 2;
        int leftMaxSum = maxSubRec(a, left, center);
        int rightMaxSum = maxSubRec(a, center + 1, right);

        int leftBorderMaxSum = 0, leftBorderThisSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderThisSum += a[i];
            if (leftBorderMaxSum < leftBorderThisSum) {
                leftBorderMaxSum = leftBorderThisSum;
            }
        }

        int rightBorderMaxSum = 0, rightBorderThisSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderThisSum += a[i];
            if (rightBorderMaxSum < rightBorderThisSum) {
                rightBorderMaxSum = rightBorderThisSum;
            }
        }

        return max(leftMaxSum, rightMaxSum, leftBorderMaxSum + rightBorderMaxSum);
    }

    private static int max(int... ints) {
        if (ints == null || ints.length == 0) {
            return -1;
        }

        int max = ints[0];

        for (int i : ints) {
            if (max < i) {
                max = i;
            }
        }

        return max;
    }

    /**
     * 方法四：最优起点
     * 最大子序列和的前缀子序列和不是负值
     */
    public static int maxSubSum4(int[] a) {
        int maxSum = 0, thisSum = 0;

        for (int i = 0; i < a.length; i++) {
            thisSum += a[i];

            if (maxSum < thisSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }

        return maxSum;
    }

    public static void main(String[] args) {
        int[] a = {4, -3, 5, -2, -1, 2, 6, -2};
        System.out.println(maxSubSum4(a));
    }
}
