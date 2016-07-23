/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.db;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
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
@Table(name = "transport_vehicle")
public class TransportVehicle implements Serializable {

    // This specifies all types of Vehicals
    public static enum VehicalType {

        VAN
    }

    // To Desiribe the Status of the Vehical
    public static enum VehicalStatus {

        ACTIVE,
        INACTIVE
    }

    @Id
    @SequenceGenerator(name = "transport_vehical_seq", sequenceName = "transport_vehical_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transport_vehical_seq")
    private long id;
    @Column(name = "number", nullable = false, unique = true)
    private String number;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "available_space", nullable = false)
    private int availableSpace;
    @Enumerated
    @Column(name = "type", nullable = false)
    private VehicalType type = VehicalType.VAN;
    @Enumerated
    @Column(name = "status", nullable = false)
    private VehicalStatus status = VehicalStatus.ACTIVE;
    @Embedded
    private Dimension dimension;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAvailableSpace() {
        return availableSpace;
    }

    public void setAvailableSpace(int availableSpace) {
        this.availableSpace = availableSpace;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public VehicalType getType() {
        return type;
    }

    public void setType(VehicalType type) {
        this.type = type;
    }

    public VehicalStatus getStatus() {
        return status;
    }

    public void setStatus(VehicalStatus status) {
        this.status = status;
    }

    public Dimension getDimension() {
        return dimension;
    }

    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    @Override
    public String toString() {
        return "TransportVehical{" + "number=" + number + ", type=" + type + ", status=" + status + '}';
    }
}
