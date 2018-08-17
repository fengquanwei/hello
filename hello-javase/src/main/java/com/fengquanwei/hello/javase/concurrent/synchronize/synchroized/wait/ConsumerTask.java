package com.fengquanwei.hello.javase.concurrent.synchronize.synchroized.wait;

/**
 * 消费者任务
 *
 * @author fengquanwei
 * @create 2018/1/26 19:54
 **/
public class ConsumerTask implements Runnable {
    private EventStorage storage;

    public ConsumerTask(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.get();
        }
    }
}
