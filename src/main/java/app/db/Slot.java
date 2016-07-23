/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.db;

import java.io.Serializable;
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
@Table(name = "slot")
public class Slot implements Serializable {

    @Id
    @SequenceGenerator(name = "slot_seq", sequenceName = "slot_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "slot_seq")
    private long id;
    @Column(name = "booked_from")
    private long from;
    @Column(name = "booked_to")
    private long to;
    @Column(name = "order_id")
    private long orderId;
    @Column(name = "allotted_date")
    @Temporal(TemporalType.DATE)
    private Date allottedDate;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getAllottedDate() {
        return allottedDate;
    }

    public void setAllottedDate(Date allottedDate) {
        this.allottedDate = allottedDate;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

}
