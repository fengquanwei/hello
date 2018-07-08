package com.fengquanwei.hello.javase.jvm.reference;

import java.lang.ref.SoftReference;

/**
 * SoftRef
 * -Xmx10m
 * 当内存不足时软引用对象被回收
 *
 * @author fengquanwei
 * @create 2018/7/8 23:56
 **/
public class SoftRef {
    public static void main(String[] args) {
        User user = new User(1, "Lask");
        SoftReference<User> userSoftReference = new SoftReference(user);
        user = null;
        System.out.println(userSoftReference.get());

        System.gc();
        System.out.println(userSoftReference.get());

        byte[] bytes = new byte[7 * 911 * 1024];
        System.gc();
        System.out.println(userSoftReference.get());

    }
}
