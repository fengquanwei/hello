package com.fengquanwei.hello.javase.concurrent.synchronize.synchroized.block;

/**
 * 售票处一任务
 *
 * @author fengquanwei
 * @create 2018/1/24 21:00
 **/
public class TicketOffice1Task implements Runnable {
    private Cinema cinema;

    public TicketOffice1Task(Cinema cinema) {
        this.cinema = cinema;
    }

    @Override
    public void run() {
        cinema.sellTickets1(3);
        cinema.sellTickets1(2);
        cinema.sellTickets2(2);
        cinema.returnTickets1(3);
        cinema.sellTickets1(5);
        cinema.sellTickets2(2);
        cinema.sellTickets2(2);
        cinema.sellTickets2(2);
    }
}
