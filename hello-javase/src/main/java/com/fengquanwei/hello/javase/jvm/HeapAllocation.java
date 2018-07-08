package com.fengquanwei.hello.javase.jvm;

import java.math.BigDecimal;

/**
 * 堆内存分配
 * -Xmx20m -Xms5m -XX:+PrintCommandLineFlags -XX:+PrintGCDetails -XX:+UseSerialGC
 *
 * @author fengquanwei
 * @create 2018/7/7 17:11
 **/
public class HeapAllocation {
    public static void main(String[] args) {
        printMemory();

        byte[] bytes1 = new byte[1 * 1024 * 1024];
        System.out.println("分配了 1M 空间给数组");

        printMemory();

        byte[] bytes2 = new byte[4 * 1024 * 1024];
        System.out.println("分配了 4M 空间给数组");

        printMemory();
    }

    private static void printMemory() {
        // -Xmx
        System.out.println("最大可用内存: " + div(Runtime.getRuntime().maxMemory(), 1024 * 1024) + "M");
        // -Xms < 当前总共内存 < -Xmx
        System.out.println("当前总共内存: " + div(Runtime.getRuntime().totalMemory(), 1024 * 1024) + "M");
        // 当前总共内存 - 当前使用内存
        System.out.println("当前空闲内存: " + div(Runtime.getRuntime().freeMemory(), 1024 * 1024) + "M");
    }

    private static double div(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, 4, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
}
