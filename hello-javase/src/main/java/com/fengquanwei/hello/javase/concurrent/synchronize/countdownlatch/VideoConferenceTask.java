package com.fengquanwei.hello.javase.concurrent.synchronize.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * 视频会议任务
 *
 * @author fengquanwei
 * @create 2018/1/27 23:41
 **/
public class VideoConferenceTask implements Runnable {
    private final CountDownLatch countDownLatch;

    public VideoConferenceTask(int number) {
        this.countDownLatch = new CountDownLatch(number);
    }

    // 与会者到达会议室
    public void arrive(String name) {
        // 倒计时
        countDownLatch.countDown();
        // 打印没有到达的与会者的数目
        System.out.printf("[%s] %s has arrived. Waiting for %d participants.\n", Thread.currentThread().getName(), name, countDownLatch.getCount());
    }

    @Override
    public void run() {
        System.out.printf("[%s] Initialization: %d participants.\n", Thread.currentThread().getName(), countDownLatch.getCount());
        try {
            // 等待所有与会者到达
            countDownLatch.await();
            System.out.printf("[%s] All the participants have come. Let's start...\n.", Thread.currentThread().getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
