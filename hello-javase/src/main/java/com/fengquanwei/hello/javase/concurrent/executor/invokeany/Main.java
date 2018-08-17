package com.fengquanwei.hello.javase.concurrent.executor.invokeany;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 运行多个任务并处理第一个结果
 *
 * @author fengquanwei
 * @create 2018/2/1 16:07
 **/
public class Main {
    public static void main(String[] args) {
        List<ValidatorTask> validatorTaskList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            validatorTaskList.add(new ValidatorTask("username" + i, "password" + i));
        }

        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

        String result;
        try {
            result = executor.invokeAny(validatorTaskList);
            System.out.printf("[%s] Result: %s\n", Thread.currentThread().getName(), result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        System.out.printf("[%s] End of Execution\n", Thread.currentThread().getName());
    }
}
