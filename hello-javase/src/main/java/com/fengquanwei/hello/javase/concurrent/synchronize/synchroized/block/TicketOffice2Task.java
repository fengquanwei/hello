package com.fengquanwei.hello.javase.concurrent.synchronize.synchroized.block;

/**
 * 售票处二任务
 *
 * @author fengquanwei
 * @create 2018/1/24 21:01
 **/
public class TicketOffice2Task implements Runnable {
    private Cinema cinema;

    public TicketOffice2Task(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public void run() {
        cinema.sellTickets2(3);
        cinema.sellTickets2(4);
        cinema.sellTickets1(2);
        cinema.sellTickets1(1);
        cinema.returnTickets2(2);
        cinema.sellTickets1(3);
        cinema.sellTickets2(2);
        cinema.sellTickets1(2);
    }
}
