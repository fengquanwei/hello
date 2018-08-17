package com.fengquanwei.hello.javase.concurrent.synchronize.countdownlatch;

import java.util.concurrent.TimeUnit;

/**
 * 与会者任务
 *
 * @author fengquanwei
 * @create 2018/1/27 23:46
 **/
public class ParticipantTask implements Runnable {
    private VideoConferenceTask videoConferenceTask;
    private String name;

    public ParticipantTask(VideoConferenceTask videoConferenceTask, String name) {
        this.videoConferenceTask = videoConferenceTask;
        this.name = name;
    }

    @Override
    public void run() {
        long duration = (long) (Math.random() * 10);
        try {
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 到达会议室
        videoConferenceTask.arrive(name);
    }
}
