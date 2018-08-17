package com.fengquanwei.hello.javase.concurrent.thread.daemon;

import java.util.Date;

/**
 * Event
 *
 * @author fengquanwei
 * @create 2018/1/23 20:49
 **/
public class Event {
    private Date date;
    private String event;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }
}
