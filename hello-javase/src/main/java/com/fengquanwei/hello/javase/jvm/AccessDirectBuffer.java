package com.fengquanwei.hello.javase.jvm;

import java.nio.ByteBuffer;

/**
 * 直接内存的读写速度快于堆内存，但是申请内存速度慢于堆空间。
 * 直接内存适合申请次数较少，访问较频繁的场合。
 *
 * @author fengquanwei
 * @create 2018/7/7 18:18
 **/
public class AccessDirectBuffer {
    public static void access(boolean direct) {
        long startTime = System.currentTimeMillis();

        ByteBuffer buffer;
        if (direct) {
            buffer = ByteBuffer.allocateDirect(500);
        } else {
            buffer = ByteBuffer.allocate(500);
        }

        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 99; j++) {
                buffer.putInt(j);
            }
            buffer.flip();
            for (int j = 0; j < 99; j++) {
                buffer.getInt();
            }
            buffer.clear();
        }

        long endTime = System.currentTimeMillis();
        if (direct) {
            System.out.println("Direct Access: " + (endTime - startTime));
        } else {
            System.out.println("Buffer Access: " + (endTime - startTime));
        }
    }

    public static void allocate(boolean direct) {
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 200000; i++) {
            if (direct) {
                ByteBuffer buffer = ByteBuffer.allocateDirect(1000);
            } else {
                ByteBuffer buffer = ByteBuffer.allocate(1000);
            }
        }
        long endTime = System.currentTimeMillis();

        if (direct) {
            System.out.println("Direct Allocate: " + (endTime - startTime));
        } else {
            System.out.println("Buffer Allocate: " + (endTime - startTime));
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            access(true);
            access(false);
            System.out.println();
        }

        for (int i = 0; i < 10; i++) {
            allocate(true);
            allocate(false);
            System.out.println();
        }
    }
}
