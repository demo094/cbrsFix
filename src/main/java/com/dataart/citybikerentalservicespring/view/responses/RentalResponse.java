package com.dataart.citybikerentalservicespring.view.responses;

/**
 * Created by mkrasowski on 14.10.2016.
 */
public class RentalResponse {
    private String rentStatus;

    public RentalResponse(String rentStatus) {
        this.rentStatus = rentStatus;
    }

    public String getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(String rentStatus) {
        this.rentStatus = rentStatus;
    }
}
