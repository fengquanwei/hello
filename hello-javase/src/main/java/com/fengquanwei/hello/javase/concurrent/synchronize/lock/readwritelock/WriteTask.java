package com.fengquanwei.hello.javase.concurrent.synchronize.lock.readwritelock;

/**
 * 写入任务
 *
 * @author fengquanwei
 * @create 2018/1/26 20:58
 **/
public class WriteTask implements Runnable {
    private PricesInfo pricesInfo;

    public WriteTask(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.printf("[%s] Modify the prices start\n", Thread.currentThread().getName());
            pricesInfo.setPrices(Math.random() * 10, Math.random() * 8);
            System.out.printf("[%s] Modify the prices end.\n", Thread.currentThread().getName());
            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
