package com.fengquanwei.hello.javase.concurrent.executor.fixedthreadpool;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 服务器（处理任务）
 *
 * @author fengquanwei
 * @create 2018/1/31 19:59
 **/
public class Server {
    private ThreadPoolExecutor executor;

    public Server() {
        this.executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);
    }

    public void executeTask(Task task) {
        System.out.printf("[%s] Server: Task arrived. Task name: %s\n", Thread.currentThread().getName(), task.getName());
        executor.execute(task);
        System.out.printf("[%s] Server: Task executed. Task name: %s, Pool size: %d, Active count: %d, Completed tasks: %d, Task count: %d\n", Thread.currentThread().getName(), task.getName(), executor.getPoolSize(), executor.getActiveCount(), executor.getCompletedTaskCount(), executor.getTaskCount());
    }

    public void endServer() {
        executor.shutdown();
    }
}
