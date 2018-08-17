package com.fengquanwei.hello.javase.concurrent.thread.daemon;

import java.util.Date;
import java.util.Deque;
import java.util.concurrent.TimeUnit;

/**
 * 写入任务
 *
 * @author fengquanwei
 * @create 2018/1/23 20:49
 **/
public class WriteTask implements Runnable {
    private Deque<Event> deque;

    public WriteTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            Event event = new Event();
            event.setDate(new Date());
            event.setEvent("Event-" + Thread.currentThread().getId() + "-" + i);

            deque.addFirst(event);
            System.out.printf("[%s] Add event: %s, deque size: %d\n", Thread.currentThread().getName(), event.getEvent(), deque.size());

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
