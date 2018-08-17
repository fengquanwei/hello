package com.fengquanwei.hello.javase.concurrent.executor.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 阶乘计算任务
 *
 * @author fengquanwei
 * @create 2018/2/1 15:29
 **/
public class FactorialCalculatorTask implements Callable<Integer> {
    private Integer number;

    public FactorialCalculatorTask(Integer number) {
        this.number = number;
    }

    @Override
    public Integer call() throws Exception {
        int result = 1;

        if (number == 0 || number == 1) {
            result = 1;
        } else {
            for (int i = 2; i <= number; i++) {
                result *= i;
                TimeUnit.MILLISECONDS.sleep(20);
            }
        }

        System.out.printf("[%s] Number: %d, Result: %d\n", Thread.currentThread().getName(), number, result);

        return result;
    }
}
