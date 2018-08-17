package com.fengquanwei.hello.javase.concurrent.thread.join;

import java.util.Date;

/**
 * 等待线程终止
 *
 * @author fengquanwei
 * @create 2018/1/23 20:36
 **/
public class Main {
    public static void main(String[] args) {
        DataSourceLoadTask dataSourceLoadTask = new DataSourceLoadTask();
        Thread dataSourceLoadTaskThread = new Thread(dataSourceLoadTask, "DataSourceLoadTaskThread");
        dataSourceLoadTaskThread.start();

        NetworkConnectionLoadTask networkConnectionLoadTask = new NetworkConnectionLoadTask();
        Thread networkConnectionLoadTaskThread = new Thread(networkConnectionLoadTask, "NetworkConnectionLoadTaskThread");
        networkConnectionLoadTaskThread.start();

        try {
            dataSourceLoadTaskThread.join();
            networkConnectionLoadTaskThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("[%s] Configuration has been loaded. Date: %s\n", Thread.currentThread().getName(), new Date());
    }
}
