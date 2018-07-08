package com.fengquanwei.hello.javase.jvm;

/**
 * 可以复活的对象
 *
 * @author fengquanwei
 * @create 2018/7/8 23:15
 **/
public class CanReliveObj {
    public static CanReliveObj obj;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("CanReliveObj finalize called");
        obj = this;
    }

    @Override
    public String toString() {
        return "I am CanReliveObj";
    }

    public static void main(String[] args) throws InterruptedException {
        obj = new CanReliveObj();

        System.out.println("The 1st gc");
        obj = null;
        System.gc();
        Thread.sleep(1000);

        if (obj == null) {
            System.out.println("obj is null");
        } else {
            System.out.println("obj live");
        }

        System.out.println("The 2nd gc");
        obj = null;
        System.gc();
        Thread.sleep(1000);

        if (obj == null) {
            System.out.println("obj is null");
        } else {
            System.out.println("obj live");
        }
    }
}
