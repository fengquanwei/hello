package com.fengquanwei.hello.javase.concurrent.thread.daemon;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 守护线程
 * 单纯测试守护线程，线程不安全的测试用例
 *
 * @author fengquanwei
 * @create 2018/1/23 20:57
 **/
public class Main {
    public static void main(String[] args) {
        Deque<Event> deque = new ArrayDeque<>();

        WriteTask writeTask = new WriteTask(deque);
        for (int i = 0; i < 3; i++) {
            Thread writeTaskThread = new Thread(writeTask, "WriteTaskThread-" + i);
            writeTaskThread.start();
        }

        CleanTask cleanTask = new CleanTask(deque);
        Thread cleanThread = new Thread(cleanTask, "CleanTaskThread");
        cleanThread.setDaemon(true); // 设置清理线程为守护线程
        cleanThread.start();
    }
}
