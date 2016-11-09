package com.dataart.citybikerentalservicespring.view.responses;

import com.dataart.citybikerentalservicespring.persistence.model.RentalHistory;

/**
 * Created by mkrasowski on 09.11.2016.
 */
public class RentalSynchroResponse {
    private Long rentalBeginTime;

    public RentalSynchroResponse(RentalHistory rentalHistory) {
        this.rentalBeginTime = rentalHistory.getBeginTime().toEpochMilli();
    }

    public Long getRentalBeginTime() {
        return rentalBeginTime;
    }

    public void setRentalBeginTime(Long rentalBeginTime) {
        this.rentalBeginTime = rentalBeginTime;
    }
}
