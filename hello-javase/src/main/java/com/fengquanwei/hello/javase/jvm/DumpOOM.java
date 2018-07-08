package com.fengquanwei.hello.javase.jvm;

import java.util.Vector;

/**
 * DumpOOM
 * -Xmx20m -Xms5m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/opt/tmp/hello.dump "-XX:OnOutOfMemoryError=/opt/script/printStack.sh %p"
 *
 * @author fengquanwei
 * @create 2018/7/7 18:01
 **/
public class DumpOOM {
    public static void main(String[] args) {
        Vector vector = new Vector();
        for (int i = 0; i < 25; i++) {
            vector.add(new byte[1 * 1024 * 1024]);
        }
    }
}
