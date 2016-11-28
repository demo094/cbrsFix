package com.dataart.citybikerentalservicespring.view.responses;

/**
 * Created by mkrasowski on 31.10.2016.
 */
public class AuthenticationErrorResponse {
    private String message;
    private Exception exception;

    public AuthenticationErrorResponse(String message, Exception exception) {
        this.message = message;
        this.exception = exception;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

}
