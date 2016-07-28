/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.db;

import app.AppSettingsImpl;
import app.util.MyCalendar;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author NavNag
 */
@Entity
@Table(name = "slot_history_by_day")
public class SlotHistoryByDay implements Serializable {

    @Id
    @SequenceGenerator(name = "slot_history_by_day_seq", sequenceName = "slot_history_by_day_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "slot_history_by_day_seq")
    private long id;
    @Column(name = "history_day")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(name = "v1")
    private int v1;
    @Column(name = "v2")
    private int v2;
    @Column(name = "v3")
    private int v3;
    @Column(name = "v4")
    private int v4;
    @Column(name = "create_at")
    private long createAt = System.currentTimeMillis();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getV1() {
        return v1;
    }

    public void setV1(int v1) {
        this.v1 = v1;
    }

    public int getV2() {
        return v2;
    }

    public void setV2(int v2) {
        this.v2 = v2;
    }

    public int getV3() {
        return v3;
    }

    public void setV3(int v3) {
        this.v3 = v3;
    }

    public int getV4() {
        return v4;
    }

    public void setV4(int v4) {
        this.v4 = v4;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    public Slot book(int count) {
        Slot slotObj = null;
        Calendar c = Calendar.getInstance();
        //c.set(Calendar.HOUR_OF_DAY, 18);
        //c.set(Calendar.MINUTE, 1);
        MyCalendar cal = new MyCalendar(c);
        // getting the slot based on the time
        AppSettingsImpl.AvailableSlots slot = AppSettingsImpl.AvailableSlots.getByTime(cal);
        AppSettingsImpl.AvailableSlots allotedSlot = null;
        if (slot == null) {
            slot = AppSettingsImpl.AvailableSlots.SLOT1;
        }
        // Now checking the availability by each vehicle
        switch (slot) {
            case SLOT1: {
                if (v1 >= count) {
                    v1 -= count;
                    allotedSlot = AppSettingsImpl.AvailableSlots.SLOT1;
                    break;
                }
            }
            case SLOT2: {
                if (v2 >= count) {
                    v2 -= count;
                    allotedSlot = AppSettingsImpl.AvailableSlots.SLOT2;
                    break;
                }
            }
            case SLOT3: {
                if (v3 >= count) {
                    v3 -= count;
                    allotedSlot = AppSettingsImpl.AvailableSlots.SLOT3;
                    break;
                }
            }
            case SLOT4: {
                if (v4 >= count) {
                    v4 -= count;
                    allotedSlot = AppSettingsImpl.AvailableSlots.SLOT4;
                    break;
                }
            }
        }
        // if allotted properly
        if (allotedSlot != null) {
            slotObj = new Slot();
            slotObj.setFrom(allotedSlot.getStart());
            slotObj.setTo(allotedSlot.getEnd());
        }
        return slotObj;
    }
}
