package com.dataart.citybikerentalservicespring.view.responses;

import com.dataart.citybikerentalservicespring.persistence.model.Bike;
import com.dataart.citybikerentalservicespring.persistence.model.RentalHistory;
import com.dataart.citybikerentalservicespring.persistence.model.User;

/**
 * Created by mkrasowski on 09.11.2016.
 */
public class RentalSynchroResponse {
    private Long rentalBeginTime;

    private Bike bike;

    public RentalSynchroResponse(User user, RentalHistory rentalHistory) {
        this.rentalBeginTime = rentalHistory.getBeginTime().toEpochMilli();
        this.bike = user.getBike();
    }

    public Long getRentalBeginTime() {
        return rentalBeginTime;
    }

    public void setRentalBeginTime(Long rentalBeginTime) {
        this.rentalBeginTime = rentalBeginTime;
    }

    public Bike getBike() {
        return bike;
    }

    public void setBike(Bike bike) {
        this.bike = bike;
    }
}
