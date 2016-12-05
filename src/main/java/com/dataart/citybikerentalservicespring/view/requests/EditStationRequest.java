package com.dataart.citybikerentalservicespring.view.requests;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by mkrasowski on 27.09.2016.
 */
public class EditStationRequest {
    private int id;
    @NotEmpty(message = "You have to put in station's name!")
    private String name;
    @NotEmpty(message = "You have to put in station's address!")
    private String address;
    @NotEmpty(message = "You have to put in station's city!")
    private String city;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public EditStationRequest() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
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
}
