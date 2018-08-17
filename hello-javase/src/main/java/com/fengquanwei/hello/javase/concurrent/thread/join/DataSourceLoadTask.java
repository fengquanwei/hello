package com.fengquanwei.hello.javase.concurrent.thread.join;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * 数据源加载任务
 *
 * @author fengquanwei
 * @create 2018/1/23 20:33
 **/
public class DataSourceLoadTask implements Runnable {
    @Override
    public void run() {
        System.out.printf("[%s] Data source load task begin. Date: %s\n", Thread.currentThread().getName(), new Date());

        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("[%s] Data source load task finished. Date: %s\n", Thread.currentThread().getName(), new Date());
    }
}
