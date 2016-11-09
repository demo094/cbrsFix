package com.dataart.citybikerentalservicespring.view.responses;

import java.time.Instant;

/**
 * Created by mkrasowski on 14.10.2016.
 */
public class RentalResponse {
    private String rentStatus;
    private Long beginTimeInMillis;

    public RentalResponse(String rentStatus) {
        this.rentStatus = rentStatus;
        this.beginTimeInMillis = Instant.now().toEpochMilli();
    }

    public String getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(String rentStatus) {
        this.rentStatus = rentStatus;
    }

    public Long getBeginTimeInMillis() {
        return beginTimeInMillis;
    }

    public void setBeginTimeInMillis(Long beginTimeInMillis) {
        this.beginTimeInMillis = beginTimeInMillis;
    }
}
