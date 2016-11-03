package com.dataart.citybikerentalservicespring.persistence.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mkrasowski on 19.09.2016.
 */

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column(nullable = false)
    private Date time;
    @ManyToOne(targetEntity = Bike.class)
    @JoinColumn(name = "bike_id", nullable = false)
    private Bike bike;
    @Column(nullable = false)
    private BigDecimal money;


    public Payment() {
    }

    public Payment(User user, Date time, Bike bike, BigDecimal money) {
        this.user = user;
        this.time = time;
        this.bike = bike;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

}
