package com.fengquanwei.hello.javase.concurrent.thread.threadfactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ThreadFactory;

/**
 * 自定义线程工厂
 *
 * @author fengquanwei
 * @create 2018/1/24 20:04
 **/
public class MyThreadFactory implements ThreadFactory {
    private int threadCount; // 线程对象的数量
    private String namePrefix; // 线程名称前缀
    private List<String> stats; // 统计数据

    public MyThreadFactory(String namePrefix) {
        this.threadCount = 0;
        this.namePrefix = namePrefix;
        this.stats = new ArrayList<>();
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, namePrefix + "-" + threadCount);
        threadCount++;
        stats.add(String.format("[%s] Create thread %d with name %s on %s\n", Thread.currentThread().getName(), thread.getId(), thread.getName(), new Date()));
        return thread;
    }

    public String getStats() {
        StringBuffer stringBuffer = new StringBuffer();

        Iterator<String> iterator = stats.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            stringBuffer.append(next);
        }

        return stringBuffer.toString();
    }
}
