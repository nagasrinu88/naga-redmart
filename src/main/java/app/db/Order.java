/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.db;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author NavNag
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @SequenceGenerator(name = "order_seq", sequenceName = "order_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_seq")
    private long id;
    @Column(name = "vehical_id")
    private long vehicalId;
    @Column(name = "name")
    private String name;
    @Column(name = "ordered_date")
    private long orderedDate = System.currentTimeMillis();
    @Column(name = "comments")
    private String comments;
    @Column(name = "slot_allotted")
    private boolean allotted;
    @Column(name = "total_items")
    private int totalItems;

    public int getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(int totalItems) {
        this.totalItems = totalItems;
    }

    public long getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(long orderedDate) {
        this.orderedDate = orderedDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public long getVehicalId() {
        return vehicalId;
    }

    public void setVehicalId(long vehicalId) {
        this.vehicalId = vehicalId;
    }

    public boolean isAllotted() {
        return allotted;
    }

    public void setAllotted(boolean allotted) {
        this.allotted = allotted;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", vehicalId=" + vehicalId + ", allotted=" + allotted + '}';
    }
}
