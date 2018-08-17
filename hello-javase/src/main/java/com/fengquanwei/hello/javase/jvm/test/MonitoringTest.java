package com.fengquanwei.hello.javase.jvm.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 监视测试（用于学习 JConsole 工具）
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 *
 * @author fengquanwei
 * @create 2017/12/19 23:08
 **/
public class MonitoringTest {

    /**
     * 内存占位符对象，一个实例大约占 64KB
     */
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    /**
     * 内存监控演示
     */
    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            // 稍作延时，令监视曲线的变化更加明显
            Thread.sleep(100);

            list.add(new OOMObject());
        }
        System.gc();
        System.out.println("end");
    }

    /**
     * 线程死循环演示
     */
    public static void createBusyThread() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {

                }
            }
        }, "testBusyThread");
        thread.start();
    }

    /**
     * 线程活锁等待演示
     */
    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "testLockThread");
        thread.start();
    }

    /**
     * 线程死锁等待演示
     */
    static class SynAddRunnable implements Runnable {
        int a, b;

        public SynAddRunnable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }
        }
    }


    public static void main(String[] args) throws InterruptedException, IOException {
        // 内存监控演示
//        fillHeap(1000);

        // 线程死循环演示
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        br.readLine();
//        createBusyThread();
//        br.readLine();

        // 线程活锁等待演示
//        Object obj = new Object();
//        createLockThread(obj);

        // 线程死锁等待演示
        Thread.sleep(5000);
        for (int i = 0; i < 100; i++) {
            new Thread(new SynAddRunnable(1, 2)).start();
            new Thread(new SynAddRunnable(2, 1)).start();
        }
    }
}