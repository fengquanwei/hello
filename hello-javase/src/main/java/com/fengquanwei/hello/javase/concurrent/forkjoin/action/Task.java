package com.fengquanwei.hello.javase.concurrent.forkjoin.action;

import java.util.List;
import java.util.concurrent.RecursiveAction;

/**
 * 任务
 *
 * @author fengquanwei
 * @create 2018/2/5 16:32
 **/
public class Task extends RecursiveAction {
    private static final long serialVersionUID = 1L;

    private List<Product> productList;
    private int first;
    private int last;
    private double increment;

    public Task(List<Product> productList, int first, int last, double increment) {
        this.productList = productList;
        this.first = first;
        this.last = last;
        this.increment = increment;
    }

    @Override
    protected void compute() {
        if (last - first < 10) {
            updatePrices();
        } else {
            int middle = (first + last) / 2;
            System.out.printf("[%s] Pending tasks: %s\n", Thread.currentThread().getName(), getQueuedTaskCount());
            Task task1 = new Task(productList, first, middle + 1, increment);
            Task task2 = new Task(productList, middle + 1, last, increment);
            invokeAll(task1, task2);
        }


    }

    private void updatePrices() {
        for (int i = first; i < last; i++) {
            Product product = productList.get(i);
            product.setPrice(product.getPrice() * (1 + increment));
        }
    }
}
