package com.fengquanwei.hello.javase.jdk.io.channel;

import java.io.IOException;

/**
 * 文件加锁机制
 *
 * @author fengquanwei
 * @create 2017/7/3 22:43
 **/
public class FileLockUsage {
    public static void main(String[] args) throws IOException {
//        FileChannel channel = FileChannel.open(Paths.get("/tmp/adobegc.log"));
//        try (FileLock lock = channel.lock()) {
//
//        }

        System.out.println((int)'A');
        System.out.println("\u0041");
        System.out.println("\0101");
    }
}
