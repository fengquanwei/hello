package com.fengquanwei.hello.javase.concurrent.synchronize.exchanger;

import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * 生产者任务
 *
 * @author fengquanwei
 * @create 2018/1/31 17:33
 **/
public class ProducerTask implements Runnable {
    // 生产者和消费者进行交换的数据结构
    private List<String> buffer;
    // 同步生产者和消费者交换对象
    private final Exchanger<List<String>> exchanger;

    public ProducerTask(List<String> buffer, Exchanger<List<String>> exchanger) {
        this.buffer = buffer;
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        int cycle = 1;
        for (int i = 0; i < 10; i++) {
            System.out.printf("[%s] Cycle: %d\n", Thread.currentThread().getName(), cycle);
            for (int j = 0; j < 10; j++) {
                String message = "Event-" + ((i * 10) + j);
                System.out.printf("[%s] Message: %s\n", Thread.currentThread().getName(), message);
                buffer.add(message);
            }

            try {
                buffer = exchanger.exchange(buffer);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.printf("[%s] Buffer size: %d\n", Thread.currentThread().getName(), buffer.size());
            cycle++;
        }
    }
}
