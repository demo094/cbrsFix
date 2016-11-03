package com.dataart.citybikerentalservicespring.persistence.model;

import javax.persistence.*;
import java.time.Instant;

/**
 * Created by mkrasowski on 06.09.2016.
 */

@Entity
@Table(name = "rental_history")
public class RentalHistory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(targetEntity = Bike.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "bike_id", nullable = false)
    private Bike bike;
    @ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(name = "begin_time", nullable = false)
    private Instant beginTime;
    @ManyToOne(targetEntity = Slot.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "begin_slot_id", nullable = false)
    private Slot beginSlot;
    @ManyToOne(targetEntity = Slot.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "end_slot_id")
    private Slot endSlot;
    @Column(name = "end_time")
    private Instant endTime;

    public RentalHistory() {
    }

    public RentalHistory(Bike bike, User user, Instant beginTime, Slot beginSlot, Slot endSlot) {
        this.bike = bike;
        this.user = user;
        this.beginTime = beginTime;
        this.beginSlot = beginSlot;
        this.endSlot = endSlot;
    }

    public RentalHistory(Bike bike, User user, Slot slot) {
        this.bike = bike;
        this.user = user;
        this.beginSlot = slot;
        this.beginTime = Instant.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Instant getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Instant beginTime) {
        this.beginTime = beginTime;
    }

    public Slot getBeginSlot() {
        return beginSlot;
    }

    public void setBeginSlot(Slot beginSlot) {
        this.beginSlot = beginSlot;
    }

    public Slot getEndSlot() {
        return endSlot;
    }

    public void setEndSlot(Slot endSlot) {
        this.endSlot = endSlot;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }
}
