package com.fengquanwei.hello.javase.concurrent.thread.info;

/**
 * 线程信息
 *
 * @author fengquanwei
 * @create 2018/1/21 20:01
 **/
public class Main {
    public static void main(String[] args) {
        Thread[] threads = new Thread[10];
        Thread.State[] states = new Thread.State[10];

        for (int i = 0; i < 10; i++) {
            // 创建线程
            threads[i] = new Thread(new CalculateTask(i));

            // 设置线程优先级
            if (i % 2 == 0) {
                threads[i].setPriority(Thread.MAX_PRIORITY);
            } else {
                threads[i].setPriority(Thread.MIN_PRIORITY);
            }

            // 设置线程名称
            threads[i].setName("CalculateTaskThread-" + i);
        }

        // 保存线程状态
        for (int i = 0; i < 10; i++) {
            System.out.printf("[%s] %s state: %s\n", Thread.currentThread().getName(), threads[i].getName(), threads[i].getState());
            states[i] = threads[i].getState();
        }

        // 启动线程
        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }

        boolean finish = false;
        while (!finish) {
            for (int i = 0; i < 10; i++) {
                if (threads[i].getState() != states[i]) {
                    System.out.printf("[%s] Thread ID: %d, Thread Name: %s, Thread Priority: %d, Thread Old State: %s, Thread New State: %s\n",
                            Thread.currentThread().getName(),
                            threads[i].getId(),
                            threads[i].getName(),
                            threads[i].getPriority(),
                            states[i],
                            threads[i].getState());

                    states[i] = threads[i].getState();
                }
            }

            finish = true;
            for (int i = 0; i < 10; i++) {
                finish = finish && (threads[i].getState() == Thread.State.TERMINATED);
            }
        }

    }
}
