package com.fengquanwei.hello.javase.concurrent.executor.completionservice;

import java.util.concurrent.CompletionService;

/**
 * 报告请求任务
 *
 * @author fengquanwei
 * @create 2018/2/5 14:00
 **/
public class ReportRequestTask implements Runnable {
    private String name;
    private CompletionService<String> completionService;

    public ReportRequestTask(String name, CompletionService<String> completionService) {
        this.name = name;
        this.completionService = completionService;
    }

    @Override
    public void run() {
        ReportGenerateTask reportGenerateTask = new ReportGenerateTask(name, "ReportTitle");
        completionService.submit(reportGenerateTask);
    }
}
