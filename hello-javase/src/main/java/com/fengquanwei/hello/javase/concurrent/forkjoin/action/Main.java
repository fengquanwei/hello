package com.fengquanwei.hello.javase.concurrent.forkjoin.action;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

/**
 * Main
 *
 * @author fengquanwei
 * @create 2018/2/5 16:39
 **/
public class Main {
    public static void main(String[] args) {
        List<Product> productList = generate(10000);

        Task task = new Task(productList, 0, productList.size(), 0.20);

        ForkJoinPool forkJoinPool = new ForkJoinPool();
        forkJoinPool.execute(task);

        do {
            System.out.printf("[%s] Thread count: %d, Thread steal: %d, Parallelism: %d\n", Thread.currentThread().getName(), forkJoinPool.getActiveThreadCount(), forkJoinPool.getStealCount(), forkJoinPool.getParallelism());
            try {
                TimeUnit.MILLISECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!task.isDone());

        forkJoinPool.shutdown();

        if (task.isCompletedNormally()) {
            System.out.printf("[%s] The process has completed normally\n", Thread.currentThread().getName());
        }

        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            if (product.getPrice() != 12) {
                System.out.printf("[%s] Product %s: %f\n", Thread.currentThread().getName(), product.getName(), product.getPrice());
            }
        }

        System.out.printf("[%s] End\n", Thread.currentThread().getName());
    }

    private static List<Product> generate(int size) {
        List<Product> productList = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Product product = new Product();
            product.setName("Product-" + i);
            product.setPrice(10);
            productList.add(product);
        }

        return productList;
    }
}
