package com.fengquanwei.hello.designpattern.singleton;

/**
 * StaticSingleton
 *
 * @author fengquanwei
 * @create 2018/8/19 18:42
 **/
public class StaticSingleton {
    private StaticSingleton() {
    }

    private static class SingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }

    public static StaticSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
