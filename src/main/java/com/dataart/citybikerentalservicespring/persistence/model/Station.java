package com.dataart.citybikerentalservicespring.persistence.model;


import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "station")
public class Station {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String address;
    @Column(nullable = false)
    private String city;
    @Column
    private BigDecimal latitude;
    @Column
    private BigDecimal longitude;
    @OneToMany(targetEntity = Slot.class, mappedBy = "station", fetch = FetchType.LAZY)
    private List<Slot> slotList;
//    later impl
//    private Point2D gpsCoordinates;


    public Station() {
    }

    public Station(int id) {
        this.id = id;
    }

    public Station(int id, List<Slot> slotList) {
        this.id = id;
        this.slotList = slotList;
    }

    public Station(int id, String name, String address, String city, List<Slot> slotList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.slotList = slotList;
    }

    public Station(int id, String name, String address, String city, List<Slot> slotList, BigDecimal latitude, BigDecimal longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.city = city;
        this.slotList = slotList;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Station(String name, String address, String city, BigDecimal latitude, BigDecimal longitude) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public List<Slot> getSlotList() {
        return slotList;
    }

    public void setSlotList(List<Slot> slotList) {
        this.slotList = slotList;
    }
}
