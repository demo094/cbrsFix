package com.dataart.citybikerentalservicespring.view.TO;

/**
 * Created by mkrasowski on 22.11.2016.
 */
public class FieldErrorTO {
    private String message;

    public FieldErrorTO(String message) {
        this.message = message;
    }


    public String getMessage() {
        return message;
    }
}
