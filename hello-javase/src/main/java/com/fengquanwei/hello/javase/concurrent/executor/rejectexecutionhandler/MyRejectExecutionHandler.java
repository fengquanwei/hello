package com.fengquanwei.hello.javase.concurrent.executor.rejectexecutionhandler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 自定义拒绝任务处理器
 *
 * @author fengquanwei
 * @create 2018/2/5 14:31
 **/
public class MyRejectExecutionHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.printf("[%s] The task %s has been rejected\n", Thread.currentThread().getName(), r.toString());
        System.out.printf("[%s] Executor: %s, Executor terminating: %s, Executor terminated: %s\n", Thread.currentThread().getName(), executor.toString(), executor.isTerminating(), executor.isTerminated());
    }
}
