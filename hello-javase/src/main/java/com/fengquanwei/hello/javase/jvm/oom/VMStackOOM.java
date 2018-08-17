package com.fengquanwei.hello.javase.jvm.oom;

/**
 * 虚拟机栈栈内存溢出
 * VM Args: -Xss2M
 * 注意！！！此程序可能导致 Windows 操作系统假死，谨慎执行！
 *
 * @author fengquanwei
 * @create 2017/8/27 22:58
 **/
public class VMStackOOM {
    private void dontStop() {
        while (true) {

        }
    }

    public void stackLeakByThread() {
        while (true) {
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    dontStop();
                }
            });
            thread.start();
        }
    }

    public static void main(String[] args) {
        VMStackOOM vmStackOOM = new VMStackOOM();
        vmStackOOM.stackLeakByThread();
    }
}
