/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.util;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author NavNag
 */
public class MyCalendar {

    private Calendar cal;

    public MyCalendar() {
        this(Calendar.getInstance());
    }

    public MyCalendar(Calendar cal) {
        this.cal = cal;
    }

    public MyCalendar moveToStartTheOfDay() {
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return this;
    }

    /**
     * this method gives the hours in 24 hours format 1-24
     *
     * @return
     */
    public int getHours() {
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public int getMinutes() {
        return cal.get(Calendar.MINUTE);
    }

    public MyCalendar nextDay() {
        return nextDay(1);
    }

    public MyCalendar nextDay(int days) {
        cal.add(Calendar.HOUR_OF_DAY, 24 * days);
        return this;
    }

    public MyCalendar nextHours() {
        return nextHours(1);
    }

    public MyCalendar nextHours(int hours) {
        cal.add(Calendar.HOUR_OF_DAY, hours);
        return this;
    }

    public Date getDate() {
        return cal.getTime();
    }

}
