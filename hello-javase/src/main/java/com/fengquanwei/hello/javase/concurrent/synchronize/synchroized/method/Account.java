package com.fengquanwei.hello.javase.concurrent.synchronize.synchroized.method;

/**
 * 账户
 *
 * @author fengquanwei
 * @create 2018/1/24 20:21
 **/
public class Account {
    private double banlance;

    public double getBanlance() {
        return banlance;
    }

    public void setBanlance(double banlance) {
        this.banlance = banlance;
    }

    public synchronized void addAmount(double amount) {
        double tmp = this.banlance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp += amount;
        this.banlance = tmp;
    }

    public synchronized void subtractAmount(double amount) {
        double tmp = this.banlance;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tmp -= amount;
        this.banlance = tmp;
    }
}
