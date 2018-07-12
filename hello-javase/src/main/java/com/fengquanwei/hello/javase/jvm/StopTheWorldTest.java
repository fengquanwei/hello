package com.fengquanwei.hello.javase.jvm;

import java.util.HashMap;

/**
 * Stop The World Test
 * -Xmx1g -Xms1g -Xmn512k -XX:+UseSerialGC -Xloggc:/opt/log/gc/gc.log -XX:+PrintGCDetails
 *
 * @author fengquanwei
 * @create 2018/7/12 23:14
 **/
public class StopTheWorldTest {
    public static class MyThread extends Thread {
        HashMap map = new HashMap();

        @Override
        public void run() {
            try {
                while (true) {
                    if (map.size() * 512 / 1024 / 1024 >= 900) {
                        map.clear();
                    }
                    byte[] bytes;
                    for (int i = 0; i < 100; i++) {
                        bytes = new byte[512];
                        map.put(System.nanoTime(), bytes);
                    }
                    Thread.sleep(1);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class PrintThread extends Thread {
        public static final long startTime = System.currentTimeMillis();

        @Override
        public void run() {
            try {
                while (true) {
                    long t = System.currentTimeMillis() - startTime;
                    System.out.println(t / 1000 + "." + t % 1000);
                    Thread.sleep(100);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        PrintThread printThread = new PrintThread();

        myThread.start();
        printThread.start();
    }
}
