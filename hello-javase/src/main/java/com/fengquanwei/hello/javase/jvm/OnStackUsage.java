package com.fengquanwei.hello.javase.jvm;

/**
 * 栈上分配（测试失败）
 * -server -Xmx10m -Xms10m -XX:+DoEscapeAnalysis -XX:+PrintGC -XX:-UseTLAB -XX:+EliminateAllocations
 *
 * @author fengquanwei
 * @create 2018/7/8 01:15
 **/
public class OnStackUsage {
    public static class User {
        public int id = 0;
        public String name = "";
    }

    public static void alloc() {
        User user = new User();
        user.id = 7;
        user.name = "Lask";
    }

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100000000; i++) {
            alloc();
        }
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);
    }
}
