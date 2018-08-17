package com.fengquanwei.hello.javase.concurrent.executor.completionservice;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * 报告处理任务
 *
 * @author fengquanwei
 * @create 2018/2/5 14:02
 **/
public class ReportProcessTask implements Runnable {
    private CompletionService<String> completionService;
    private boolean end;

    public ReportProcessTask(CompletionService<String> completionService) {
        this.completionService = completionService;
        this.end = false;
    }

    @Override
    public void run() {
        while (!end) {
            try {
                Future<String> future = completionService.poll(20, TimeUnit.SECONDS);
                if (future != null) {
                    String report = future.get();
                    System.out.printf("[%s] Received report: %s\n", Thread.currentThread().getName(), report);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("[%s] End\n", Thread.currentThread().getName());
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}
