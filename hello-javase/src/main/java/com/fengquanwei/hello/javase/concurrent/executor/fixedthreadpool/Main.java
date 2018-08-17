package com.fengquanwei.hello.javase.concurrent.executor.fixedthreadpool;

/**
 * 固定大小的线程池
 *
 * @author fengquanwei
 * @create 2018/1/31 20:04
 **/
public class Main {
    public static void main(String[] args) {
        Server server = new Server();
        for (int i = 0; i < 100; i++) {
            Task task = new Task("Task-" + i);
            server.executeTask(task);
        }

        server.endServer();
    }
}
