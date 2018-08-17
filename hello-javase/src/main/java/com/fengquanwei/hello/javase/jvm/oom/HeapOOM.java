package com.fengquanwei.hello.javase.jvm.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * 堆内存溢出
 *
 * @author fengquanwei
 * @create 2017/8/27 15:44
 **/
public class HeapOOM {
    static class OOMObject {

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();

        while (true) {
            list.add(new OOMObject());
        }
    }
}
