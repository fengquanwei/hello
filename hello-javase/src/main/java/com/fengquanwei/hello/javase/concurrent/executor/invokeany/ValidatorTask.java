package com.fengquanwei.hello.javase.concurrent.executor.invokeany;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * 验证器任务
 *
 * @author fengquanwei
 * @create 2018/2/1 16:04
 **/
public class ValidatorTask implements Callable<String> {
    private String username;
    private String password;

    public ValidatorTask(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String call() throws Exception {
        Random random = new Random();
        boolean result = random.nextBoolean();
        try {
            int duration = random.nextInt(10);
            System.out.printf("[%s] Validating a user(%s, %s) during %d seconds\n", Thread.currentThread().getName(), username, password, duration);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            System.out.printf("[%s] Interrupted\n", Thread.currentThread().getName());
            return username + " failure";
        }

        if (!result) {
            System.out.printf("[%s] The user(%s, %s) has not been found\n", Thread.currentThread().getName(), username, password);
            throw new Exception("Error validating user");
        }

        System.out.printf("[%s] The user(%s, %s) has been found\n", Thread.currentThread().getName(), username, password);
        return username + " success";
    }
}
