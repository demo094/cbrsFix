package com.dataart.citybikerentalservicespring.view.responses;

/**
 * Created by mkrasowski on 20.10.2016.
 */
public class CommonResponse {
    private String status;

    public CommonResponse(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
