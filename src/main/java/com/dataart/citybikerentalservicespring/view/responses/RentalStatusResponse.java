package com.dataart.citybikerentalservicespring.view.responses;

import com.dataart.citybikerentalservicespring.persistence.model.RentalHistory;
import com.dataart.citybikerentalservicespring.persistence.model.User;

/**
 * Created by mkrasowski on 09.11.2016.
 */
public class RentalStatusResponse {
    private Long rentalBeginTime;

    private boolean bikeBeingRented;

    public RentalStatusResponse(User user, RentalHistory rentalHistory) {
        if (rentalHistory != null) {
            this.rentalBeginTime = rentalHistory.getBeginTime().toEpochMilli();
        }
        this.bikeBeingRented = user.getBike() != null;
    }

    public Long getRentalBeginTime() {
        return rentalBeginTime;
    }

    public void setRentalBeginTime(Long rentalBeginTime) {
        this.rentalBeginTime = rentalBeginTime;
    }

    public boolean isBikeBeingRented() {
        return bikeBeingRented;
    }

    public void setBikeBeingRented(boolean bikeBeingRented) {
        this.bikeBeingRented = bikeBeingRented;
    }
}
