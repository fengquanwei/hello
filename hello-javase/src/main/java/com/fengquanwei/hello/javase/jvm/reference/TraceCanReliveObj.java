package com.fengquanwei.hello.javase.jvm.reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 跟踪可复活对象
 *
 * @author fengquanwei
 * @create 2018/7/9 00:25
 **/
public class TraceCanReliveObj {
    public static TraceCanReliveObj traceCanReliveObj;
    static ReferenceQueue<TraceCanReliveObj> referenceQueue = null;

    public static class CheckRefQueueThread extends Thread {
        @Override
        public void run() {
            while (true) {
                if (referenceQueue != null) {
                    PhantomReference<TraceCanReliveObj> phantomReference = null;
                    try {
                        phantomReference = (PhantomReference<TraceCanReliveObj>) referenceQueue.remove();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (phantomReference != null) {
                        System.out.println("TraceCanReliveObj is delete by GC");
                    }
                }
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("TraceCanReliveObj finalize called");
        traceCanReliveObj = this;
    }

    @Override
    public String toString() {
        return "I am TraceCanReliveObj";
    }

    public static void main(String[] args) throws InterruptedException {
        CheckRefQueueThread checkRefQueueThread = new CheckRefQueueThread();
        checkRefQueueThread.setDaemon(true);
        checkRefQueueThread.start();

        referenceQueue = new ReferenceQueue<>();
        traceCanReliveObj = new TraceCanReliveObj();
        PhantomReference<TraceCanReliveObj> phantomReference = new PhantomReference<>(traceCanReliveObj, referenceQueue);

        traceCanReliveObj = null;
        System.gc();
        Thread.sleep(1000);
        if (traceCanReliveObj == null) {
            System.out.println("traceCanReliveObj is null");
        } else {
            System.out.println("traceCanReliveObj is live");
        }

        traceCanReliveObj = null;
        System.gc();
        Thread.sleep(1000);
        if (traceCanReliveObj == null) {
            System.out.println("traceCanReliveObj is null");
        } else {
            System.out.println("traceCanReliveObj is live");
        }
    }
}
