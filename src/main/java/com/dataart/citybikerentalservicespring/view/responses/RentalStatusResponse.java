package com.dataart.citybikerentalservicespring.view.responses;

import com.dataart.citybikerentalservicespring.persistence.model.RentalHistory;
import com.dataart.citybikerentalservicespring.persistence.model.User;

/**
 * Created by mkrasowski on 09.11.2016.
 */
public class RentalStatusResponse {
    private Long rentalBeginTime;

    private boolean isBeingRented;

    public RentalStatusResponse(User user, RentalHistory rentalHistory) {
        if (rentalHistory != null) {
            this.rentalBeginTime = rentalHistory.getBeginTime().toEpochMilli();
        } else {
            this.rentalBeginTime = null;
        }
        this.isBeingRented = user.getBike() != null;
    }

    public Long getRentalBeginTime() {
        return rentalBeginTime;
    }

    public void setRentalBeginTime(Long rentalBeginTime) {
        this.rentalBeginTime = rentalBeginTime;
    }

    public boolean isBeingRented() {
        return isBeingRented;
    }

    public void setBeingRented(boolean beingRented) {
        isBeingRented = beingRented;
    }
}
