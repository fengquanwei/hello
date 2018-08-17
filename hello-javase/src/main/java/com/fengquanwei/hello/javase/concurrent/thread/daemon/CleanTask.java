package com.fengquanwei.hello.javase.concurrent.thread.daemon;

import java.util.Date;
import java.util.Deque;

/**
 * 清理任务
 *
 * @author fengquanwei
 * @create 2018/1/23 20:52
 **/
public class CleanTask implements Runnable {
    private Deque<Event> deque;

    public CleanTask(Deque<Event> deque) {
        this.deque = deque;
    }

    @Override
    public void run() {
        while (true) {
            Date date = new Date();
            clean(date);
        }
    }

    private void clean(Date date) {
        if (deque.size() == 0) {
            return;
        }

        long difference;
        do {
            Event event = deque.getLast();
            difference = date.getTime() - event.getDate().getTime();
            if (difference > 5000) {
                deque.removeLast();
                System.out.printf("[%s] Remove event: %s, deque size: %d", Thread.currentThread().getName(), event.getEvent(), deque.size());
            }
        } while (difference > 5000);
    }
}
