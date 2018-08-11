package com.fengquanwei.hello.javase.concurrent;

/**
 * 等待与通知
 *
 * @author fengquanwei
 * @create 2018/8/11 23:51
 **/
public class WaitAndNotify {
    private final static Object object = new Object();

    public static class T1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ": T1 start");
                try {
                    System.out.println(System.currentTimeMillis() + ": T1 wait on object");
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(System.currentTimeMillis() + ": T1 end");
            }
        }
    }

    public static class T2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println(System.currentTimeMillis() + ": T2 start");
                System.out.println(System.currentTimeMillis() + ": T2 notify one on object");
                object.notify();
                System.out.println(System.currentTimeMillis() + ": T2 end");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        T1 t1 = new T1();
        T2 t2 = new T2();
        t1.start();
        t2.start();
    }

}
