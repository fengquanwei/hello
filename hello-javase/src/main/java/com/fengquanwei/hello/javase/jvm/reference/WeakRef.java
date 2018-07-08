package com.fengquanwei.hello.javase.jvm.reference;

import java.lang.ref.WeakReference;

/**
 * 弱引用
 *
 * @author fengquanwei
 * @create 2018/7/9 00:22
 **/
public class WeakRef {
    public static void main(String[] args) {
        User user = new User(1, "Lask");
        WeakReference<User> userWeakReference = new WeakReference<User>(user);

        user = null;
        System.out.println(userWeakReference.get());

        System.gc();
        System.out.println(userWeakReference.get());
    }
}
