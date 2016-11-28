package com.dataart.citybikerentalservicespring.view.responses;

/**
 * Created by mkrasowski on 28.10.2016.
 */
public class StatusErrorResponse {
    private int status;
    private String message;

    public StatusErrorResponse(int status, String message) {
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
