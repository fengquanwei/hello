package com.fengquanwei.hello.javase.concurrent.synchronize.synchroized.method;

/**
 * 银行任务：扣一百次钱
 *
 * @author fengquanwei
 * @create 2018/1/24 20:24
 **/
public class BankTask implements Runnable {
    private Account account;

    public BankTask(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            account.subtractAmount(1000);
        }
    }
}
