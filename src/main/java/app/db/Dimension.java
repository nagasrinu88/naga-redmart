/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.db;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author NavNag
 */
@Embeddable
public class Dimension implements Serializable {

    @Column(name = "height")
    private int height;
    @Column(name = "width")
    private int width;
    @Column(name = "breadth")
    private int breadth;

    public Dimension() {
    }

    public Dimension(int height, int width, int breadth) {
        this.height = height;
        this.width = width;
        this.breadth = breadth;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getBreadth() {
        return breadth;
    }

    public void setBreadth(int breadth) {
        this.breadth = breadth;
    }

    @Override
    public String toString() {
        return "Dimension{" + "height=" + height + ", width=" + width + ", breadth=" + breadth + '}';
    }
}
