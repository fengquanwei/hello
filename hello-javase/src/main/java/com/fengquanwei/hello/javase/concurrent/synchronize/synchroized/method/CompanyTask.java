package com.fengquanwei.hello.javase.concurrent.synchronize.synchroized.method;

/**
 * 公司任务：充一百次钱
 *
 * @author fengquanwei
 * @create 2018/1/24 20:25
 **/
public class CompanyTask implements Runnable {
    private Account account;

    public CompanyTask(Account account) {
        this.account = account;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            account.addAmount(1000);
        }
    }
}
