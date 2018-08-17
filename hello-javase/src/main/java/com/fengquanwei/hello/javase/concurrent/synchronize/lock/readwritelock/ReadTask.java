package com.fengquanwei.hello.javase.concurrent.synchronize.lock.readwritelock;

/**
 * 读取任务
 *
 * @author fengquanwei
 * @create 2018/1/26 20:56
 **/
public class ReadTask implements Runnable {
    private PricesInfo pricesInfo;

    public ReadTask(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("[%s] Price 1: %f\n", Thread.currentThread().getName(), pricesInfo.getPrice1());
            System.out.printf("[%s] Price 2: %f\n", Thread.currentThread().getName(), pricesInfo.getPrice2());
        }
    }
}
