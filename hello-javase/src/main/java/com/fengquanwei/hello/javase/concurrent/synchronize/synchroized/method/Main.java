package com.fengquanwei.hello.javase.concurrent.synchronize.synchroized.method;

/**
 * 同步机制：synchronized 方法
 *
 * @author fengquanwei
 * @create 2018/1/24 20:26
 **/
public class Main {
    public static void main(String[] args) {
        Account account = new Account();
        account.setBanlance(1000);

        Thread companyTaskThread = new Thread(new CompanyTask(account), "CompanyTaskThread");
        Thread bankTaskThread = new Thread(new BankTask(account), "BankTaskThread");

        System.out.printf("[%s] Account initial balance: %f\n", Thread.currentThread().getName(), account.getBanlance());

        companyTaskThread.start();
        bankTaskThread.start();

        try {
            companyTaskThread.join();
            bankTaskThread.join();
            System.out.printf("[%s] Account final balance: %f\n", Thread.currentThread().getName(), account.getBanlance());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
