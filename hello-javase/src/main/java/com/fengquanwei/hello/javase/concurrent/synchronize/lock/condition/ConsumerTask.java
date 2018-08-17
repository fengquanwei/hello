package com.fengquanwei.hello.javase.concurrent.synchronize.lock.condition;

import java.util.Random;

/**
 * 消费者任务
 *
 * @author fengquanwei
 * @create 2018/1/27 14:13
 **/
public class ConsumerTask implements Runnable {
    private Buffer buffer;

    public ConsumerTask(Buffer buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (buffer.hasPendingLines()) {
            String line = buffer.get();
            processLine(line);
        }
    }

    private void processLine(String line) {
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
