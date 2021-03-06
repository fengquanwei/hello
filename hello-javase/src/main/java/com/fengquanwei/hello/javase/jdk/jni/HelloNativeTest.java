package com.fengquanwei.hello.javase.jdk.jni;

/**
 * 测试本地方法
 *
 * @author fengquanwei
 * @create 2017/8/11 10:50
 **/
public class HelloNativeTest {
    static {
        System.loadLibrary("hellonative");
    }

    /**
     * vim HelloNative.java
     * cd /Users/kilogate/IdeaProjects/hello/hello-java/src/main/java
     * javac com/kilogate/hello/java/javase/jdk/jni/HelloNative.java
     * javah com.kilogate.hello.java.HelloNative
     * mv com_kilogate_hello_java_javase_jdkapi_jni_HelloNative.h com/kilogate/hello/java/javase/jdk/jni/
     * vim HelloNative.c
     * gcc -dynamiclib -I /Library/Java/JavaVirtualMachines/jdk1.8.0_121.jdk/Contents/Home/include/ com/kilogate/hello/java/javase/jdk/jni/HelloNative.c -o libhellonative.jnilib
     * mv libhellonative.jnilib com/kilogate/hello/java/javase/jdk/jni/
     * vim HelloNativeTest.java
     * javac com/kilogate/hello/java/javase/jdk/jni/HelloNativeTest.java
     * java -Djava.library.path=com/kilogate/hello/java/javase/jdk/jni/ com.kilogate.hello.java.HelloNativeTest
     */
    public static void main(String[] args) {
        HelloNative.greeting();
    }
}
