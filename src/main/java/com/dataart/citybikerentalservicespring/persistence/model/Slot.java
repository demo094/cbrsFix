package com.dataart.citybikerentalservicespring.persistence.model;

import javax.persistence.*;

/**
 * Created by mkrasowski on 06.09.2016.
 */

@Entity
@Table(name = "slot")
public class Slot {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(targetEntity = Station.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "station_id", nullable = false)
    private Station station;
    @OneToOne(targetEntity = Bike.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "bike_id")
    private Bike bike;

    public Slot() {
    }

    public Slot(Station station) {
        this.station = station;
    }

    public Slot(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }
}
