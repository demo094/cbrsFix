package com.dataart.citybikerentalservicespring.view.responses;

/**
 * Created by mkrasowski on 20.10.2016.
 */
public class CommonResponse {
    private String message;

    public CommonResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
