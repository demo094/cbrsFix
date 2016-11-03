package com.dataart.citybikerentalservicespring.view.TO;

/**
 * Created by mkrasowski on 28.10.2016.
 */
public class ApiErrorTO {
    private int status;
    private String message;

    public ApiErrorTO(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
