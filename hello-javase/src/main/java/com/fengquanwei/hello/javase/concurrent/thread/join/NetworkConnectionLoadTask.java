package com.fengquanwei.hello.javase.concurrent.thread.join;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 网络连接加载任务
 *
 * @author fengquanwei
 * @create 2018/1/23 20:35
 **/
public class NetworkConnectionLoadTask implements Runnable {
    @Override
    public void run() {
        System.out.printf("[%s] Network connection load begin. Date: %s\n", Thread.currentThread().getName(), new Date());

        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("[%s] Network connection load finished. Date: %s\n", Thread.currentThread().getName(), new Date());
    }
}
