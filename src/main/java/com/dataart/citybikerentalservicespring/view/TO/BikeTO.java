package com.dataart.citybikerentalservicespring.view.TO;

import com.dataart.citybikerentalservicespring.persistence.model.Bike;

/**
 * Created by mkrasowski on 26.09.2016.
 */
public class BikeTO {
    public Integer bikeId;

    public BikeTO(Bike bike) {
        this.bikeId = bike.getId();
    }

    public Integer getBikeId() {
        return bikeId;
    }

    public void setBikeId(Integer bikeId) {
        this.bikeId = bikeId;
    }
}
