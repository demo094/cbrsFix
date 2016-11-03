package com.dataart.citybikerentalservicespring.exceptions;

import com.dataart.citybikerentalservicespring.constants.UserMessage;

/**
 * Created by mkrasowski on 06.09.2016.
 */

public class CbrsException extends Exception {

    private UserMessage userMessage;

    public CbrsException() {
        this.userMessage = UserMessage.SYSTEM_ERROR;
    }

    public CbrsException(UserMessage userMessage){
        this.userMessage = userMessage;
    }

    public UserMessage getUserMessage() {
        return userMessage;
    }
}
