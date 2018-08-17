package com.fengquanwei.hello.javase.concurrent.thread.interrupt;

/**
 * 质数生成任务
 *
 * @author fengquanwei
 * @create 2018/1/21 21:13
 **/
public class PrimeGenerateTask implements Runnable {
    @Override
    public void run() {
        long number = 2;

        while (true) {
            if (isPrime(number)) {
                System.out.printf("[%s] Number %d is prime\n", Thread.currentThread().getName(), number);
            }

            if (Thread.currentThread().isInterrupted()) {
                System.out.printf("[%s] Interrupted\n", Thread.currentThread().getName());
                return;
            }

            number++;
        }
    }

    /**
     * 是否为质数
     */
    private boolean isPrime(long number) {
        if (number == 2) {
            return true;
        }

        for (long i = 2; i < number; i++) {
            if ((number % i) == 0) {
                return false;
            }
        }

        return true;
    }
}
