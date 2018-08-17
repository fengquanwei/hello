package com.fengquanwei.hello.javase.concurrent.synchronize.phaser.advance;

import java.util.Date;
import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;

/**
 * 学生任务
 *
 * @author fengquanwei
 * @create 2018/1/31 16:55
 **/
public class StudentTask implements Runnable {
    private Phaser phaser;

    public StudentTask(Phaser phaser) {
        this.phaser = phaser;
    }

    @Override
    public void run() {
        System.out.printf("[%s] I am ready to do the exam. Date: %s\n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();

        System.out.printf("[%s] Is going to do the first exercise. Date: %s\n", Thread.currentThread().getName(), new Date());
        doExercise();
        System.out.printf("[%s] Has done the first exercise. Date: %s\n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();

        System.out.printf("[%s] Is going to do the second exercise. Date: %s\n", Thread.currentThread().getName(), new Date());
        doExercise();
        System.out.printf("[%s] Has done the second exercise. Date: %s\n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();

        System.out.printf("[%s] Is going to do the third exercise. Date: %s\n", Thread.currentThread().getName(), new Date());
        doExercise();
        System.out.printf("[%s] Has finished the exam. Date: %s\n", Thread.currentThread().getName(), new Date());
        phaser.arriveAndAwaitAdvance();
    }

    private void doExercise() {
        try {
            long duration = (long) (Math.random() * 10);
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
