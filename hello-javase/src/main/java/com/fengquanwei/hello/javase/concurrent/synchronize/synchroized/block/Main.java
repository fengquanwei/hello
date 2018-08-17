package com.fengquanwei.hello.javase.concurrent.synchronize.synchroized.block;

/**
 * 同步机制：synchronized 代码块
 *
 * @author fengquanwei
 * @create 2018/1/24 21:03
 **/
public class Main {
    public static void main(String[] args) {
        Cinema cinema = new Cinema();

        TicketOffice1Task ticketOffice1Task = new TicketOffice1Task(cinema);
        Thread ticketOffice1TaskThread = new Thread(ticketOffice1Task, "TicketOffice1TaskThread");

        TicketOffice2Task ticketOffice2Task = new TicketOffice2Task(cinema);
        Thread ticketOffice2TaskThread = new Thread(ticketOffice2Task, "TicketOffice2TaskThread");

        ticketOffice1TaskThread.start();
        ticketOffice2TaskThread.start();

        try {
            ticketOffice1TaskThread.join();
            ticketOffice2TaskThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("[%s] Room 1 vacancies: %d\n", Thread.currentThread().getName(), cinema.getVacancy1());
        System.out.printf("[%s] Room 2 vacancies: %d\n", Thread.currentThread().getName(), cinema.getVacancy2());
    }
}
