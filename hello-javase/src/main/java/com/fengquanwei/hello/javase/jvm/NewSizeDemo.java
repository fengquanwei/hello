package com.fengquanwei.hello.javase.jvm;

/**
 * 新生代参数配置
 * -Xmx20m -Xms20m -Xmn1m -XX:SurvirorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
 * -Xmx20m -Xms20m -Xmn7m -XX:SurvirorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
 * -Xmx20m -Xms20m -Xmn15m -XX:SurvirorRatio=2 -XX:+PrintGCDetails -XX:+UseSerialGC
 *
 * @author fengquanwei
 * @create 2018/7/7 17:44
 **/
public class NewSizeDemo {
    public static void main(String[] args) {
        byte[] bytes = null;
        for (int i = 0; i < 10; i++) {
            bytes = new byte[1 * 1024 * 1024];
        }
    }
}
