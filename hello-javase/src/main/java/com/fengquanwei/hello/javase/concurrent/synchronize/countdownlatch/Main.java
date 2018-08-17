package com.fengquanwei.hello.javase.concurrent.synchronize.countdownlatch;

/**
 * 倒计时门栓
 *
 * @author fengquanwei
 * @create 2018/1/27 23:48
 **/
public class Main {
    public static void main(String[] args) {
        // 视频会议等待十个与会者到达
        VideoConferenceTask videoConferenceTask = new VideoConferenceTask(10);
        new Thread(videoConferenceTask, "VideoConferenceTaskThread").start();

        // 十个与会者陆续到达
        for (int i = 0; i < 10; i++) {
            new Thread(new ParticipantTask(videoConferenceTask, "Participant-" + i), "ParticipantTaskThread-" + i).start();
        }
    }
}
