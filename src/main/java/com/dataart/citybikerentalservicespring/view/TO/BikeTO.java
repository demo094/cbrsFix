package com.dataart.citybikerentalservicespring.view.TO;

import com.dataart.citybikerentalservicespring.persistence.model.Bike;

/**
 * Created by mkrasowski on 26.09.2016.
 */
public class BikeTO {
    private Integer id;
    private String type;

    public BikeTO(Bike bike) {
        this.id = bike.getId();
        if(bike.getType() != null) {
            this.type = bike.getType().getTypeValue();
        }
    }

    public Integer getBikeId() {
        return id;
    }

    public void setBikeId(Integer bikeId) {
        this.id = bikeId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
