package com.fengquanwei.hello.javase.concurrent.executor.completionservice;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 报告生成任务
 *
 * @author fengquanwei
 * @create 2018/2/5 13:56
 **/
public class ReportGenerateTask implements Callable<String> {
    private String sender;
    private String title;

    public ReportGenerateTask(String sender, String title) {
        this.sender = sender;
        this.title = title;
    }

    @Override
    public String call() throws Exception {
        int duration = new Random().nextInt(10);
        System.out.printf("[%s] Generating a report(%s,%s) during %d seconds\n", Thread.currentThread().getName(), this.sender, this.title, duration);
        TimeUnit.SECONDS.sleep(duration);

        return this.sender + ":" + this.title;
    }
}
