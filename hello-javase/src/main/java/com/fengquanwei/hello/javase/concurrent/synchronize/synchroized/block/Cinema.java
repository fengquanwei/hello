package com.fengquanwei.hello.javase.concurrent.synchronize.synchroized.block;

/**
 * 电影院
 *
 * @author fengquanwei
 * @create 2018/1/24 20:45
 **/
public class Cinema {
    // 放映厅一空位数
    private long vacancy1;
    // 放映厅二空位数
    private long vacancy2;

    // 放映厅一同步控制锁
    private final Object control1;
    // 放映厅二同步控制锁
    private final Object control2;

    public Cinema() {
        this.vacancy1 = 20;
        this.vacancy2 = 20;
        this.control1 = new Object();
        this.control2 = new Object();
    }

    // 售放映厅一的影票
    public boolean sellTickets1(int number) {
        synchronized (control1) {
            if (number < vacancy1) {
                vacancy1 -= number;
                return true;
            } else {
                return false;
            }
        }
    }

    // 售放映厅二的影票
    public boolean sellTickets2(int number) {
        synchronized (control2) {
            if (number < vacancy2) {
                vacancy2 -= number;
                return true;
            } else {
                return false;
            }
        }
    }

    // 退放映厅一的影票
    public boolean returnTickets1(int number) {
        synchronized (control1) {
            vacancy1 += number;
            return true;
        }
    }

    // 退放映厅二的影票
    public boolean returnTickets2(int number) {
        synchronized (control2) {
            vacancy2 += number;
            return true;
        }
    }

    public long getVacancy1() {
        return vacancy1;
    }

    public long getVacancy2() {
        return vacancy2;
    }
}
