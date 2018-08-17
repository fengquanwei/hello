package com.fengquanwei.hello.javase.concurrent.synchronize.synchroized.wait;

/**
 * 生产者任务
 *
 * @author fengquanwei
 * @create 2018/1/26 19:53
 **/
public class ProducerTask implements Runnable {
    private EventStorage storage;

    public ProducerTask(EventStorage storage) {
        this.storage = storage;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            storage.set();
        }
    }
}
