package com.fengquanwei.hello.javase.concurrent.thread.exception.threadgroup;

/**
 * 自定义线程组
 *
 * @author fengquanwei
 * @create 2018/1/24 19:41
 **/
public class MyThreadGroup extends ThreadGroup {
    public MyThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.printf("[%s] Thrown an exception\n", t.getName());
        e.printStackTrace(System.out);

        // 中断线程组中的其他线程
        System.out.printf("[%s] Terminating the rest of the threads\n", t.getName());
        interrupt();
    }
}
