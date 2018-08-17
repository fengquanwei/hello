package com.fengquanwei.hello.javase.concurrent.synchronize.phaser.advance;

import java.util.concurrent.Phaser;

/**
 * 自定义 Phaser
 *
 * @author fengquanwei
 * @create 2018/1/31 16:39
 **/
public class MyPhaser extends Phaser {
    @Override
    protected boolean onAdvance(int phase, int registeredParties) {
        switch (phase) {
            case 0:
                return studentsArrived();
            case 1:
                return finishFirstExercise();
            case 2:
                return finishSecondExercise();
            case 3:
                return finishExam();
            default:
                return true;
        }
    }

    private boolean studentsArrived() {
        System.out.printf("[%s] The students are ready. The exam are going to start. We have %d students\n", Thread.currentThread().getName(), getRegisteredParties());

        // 继续下一阶段
        return false;
    }

    private boolean finishFirstExercise() {
        System.out.printf("[%s] All the students have finished the first exercise. It's time for the second one.\n", Thread.currentThread().getName());

        // 继续下一阶段
        return false;
    }

    private boolean finishSecondExercise() {
        System.out.printf("[%s] All the students have finished the second exercise. It's time for the third one.\n", Thread.currentThread().getName());

        // 继续下一阶段
        return false;
    }

    private boolean finishExam() {
        System.out.printf("[%s] All the students have finished the exam. Thank you for your time.\n", Thread.currentThread().getName());

        // 终止
        return true;
    }
}
