package com.fengquanwei.hello.javase.concurrent.synchronize.lock.condition;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 数据缓冲区
 *
 * @author fengquanwei
 * @create 2018/1/27 13:52
 **/
public class Buffer {
    private LinkedList<String> buffer; // 存放数据
    private int maxSize; // 最大尺寸
    private boolean pendingLines; // 缓冲区是否还有数据

    private ReentrantLock lock; // 同步修改 buffer 的代码块
    private Condition lines; // 读取数据时等待数据
    private Condition space; // 写入数据时等待空间

    public Buffer(int maxSize) {
        this.maxSize = maxSize;
        buffer = new LinkedList<>();
        lock = new ReentrantLock();
        lines = lock.newCondition();
        space = lock.newCondition();
        pendingLines = true;
    }

    public void insert(String line) {
        lock.lock();
        try {
            while (buffer.size() == maxSize) {
                space.await();
            }
            buffer.offer(line);
            System.out.printf("[%s] Inserted line: %s. Size: %d\n", Thread.currentThread().getName(), line, buffer.size());
            lines.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String get() {
        String line = null;
        lock.lock();
        try {
            while ((buffer.size() == 0) && (hasPendingLines())) {
                lines.await();
            }
            if (hasPendingLines()) {
                line = buffer.poll();
                System.out.printf("[%s] Readed line: %s. Size: %d\n", Thread.currentThread().getName(), line, buffer.size());
                space.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return line;
    }

    public void setPendingLines(boolean pendingLines) {
        this.pendingLines = pendingLines;
    }

    public boolean hasPendingLines() {
        return pendingLines || buffer.size() > 0;
    }
}
