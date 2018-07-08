package com.fengquanwei.hello.javase.jvm.reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * 软引用与引用队列
 * -Xmx10m
 *
 * @author fengquanwei
 * @create 2018/7/9 00:04
 **/
public class SoftRefQ {
    private static ReferenceQueue<User> userReferenceQueue = null;

    public static class CheckRefQueueThread extends Thread {
        @Override
        public void run() {
            while (true) {
                if (userReferenceQueue != null) {
                    UserSoftReference userSoftReference = null;
                    try {
                        userSoftReference = (UserSoftReference) userReferenceQueue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (userSoftReference != null) {
                        System.out.println("user id " + userSoftReference.uid + " is delete");
                    }
                }
            }
        }
    }

    public static class UserSoftReference extends SoftReference<User> {
        int uid;

        public UserSoftReference(User referent, ReferenceQueue<? super User> q) {
            super(referent, q);
            this.uid = referent.getId();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CheckRefQueueThread checkRefQueueThread = new CheckRefQueueThread();
        checkRefQueueThread.setDaemon(true);
        checkRefQueueThread.start();

        User user = new User(1, "Lask");
        userReferenceQueue = new ReferenceQueue<>();
        UserSoftReference userSoftReference = new UserSoftReference(user, userReferenceQueue);

        user = null;
        System.out.println(userSoftReference.get());

        System.gc();
        System.out.println(userSoftReference.get());

        byte[] bytes = new byte[7 * 911 * 1024];
        System.gc();
        System.out.println(userSoftReference.get());

        Thread.sleep(1000);
    }
}
