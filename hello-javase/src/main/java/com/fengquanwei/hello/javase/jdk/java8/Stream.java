package com.fengquanwei.hello.javase.jdk.java8;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 流
 *
 * @author fengquanwei
 * @create 2018/2/28 21:06
 **/
public class Stream {
    // 数据源：集合，数组，I/O channel，generator

    public static void main(String[] args) {
        // 1. 生成流
        List<Integer> integers = Arrays.asList(3, 2, 1);

        // 1.1 生成串行流
        java.util.stream.Stream<Integer> stream = integers.stream();
        // 1.2 生成并行流
        java.util.stream.Stream<Integer> parallelStream = integers.parallelStream();

        // 2. 聚合操作

        // 2.1 forEach
        System.out.println("========== forEach");
        integers.stream().forEach(System.out::println);

        // 2.2 map
        System.out.println("========== map");
        integers.stream().map(i -> i * i).forEach(System.out::println);

        // 2.3 filter
        System.out.println("========== filter");
        integers.stream().filter(i -> i > 1).forEach(System.out::println);

        // 2.4 limit
        System.out.println("========== limit");
        integers.stream().limit(1).forEach(System.out::println);

        // 2.5 sorted
        System.out.println("========== sorted");
        integers.stream().sorted((a, b) -> b - a).forEach(System.out::println);

        // 2.6 count
        System.out.println("========== count");
        System.out.println(integers.parallelStream().count());

        // 2.7 collect
        System.out.println("========== collect");
        List<Integer> collect = integers.stream().collect(Collectors.toList());

        // 2.8 统计
        System.out.println("========== 统计");
        IntSummaryStatistics intSummaryStatistics = integers.stream().mapToInt(x -> x * x).summaryStatistics();
        System.out.println(intSummaryStatistics.getMax());
        System.out.println(intSummaryStatistics.getMin());
        System.out.println(intSummaryStatistics.getSum());
        System.out.println(intSummaryStatistics.getAverage());

    }
}
