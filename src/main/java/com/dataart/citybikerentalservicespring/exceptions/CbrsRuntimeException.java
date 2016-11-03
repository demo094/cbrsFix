package com.dataart.citybikerentalservicespring.exceptions;

import com.dataart.citybikerentalservicespring.constants.UserMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by mkrasowski on 11.10.2016.
 */
public class CbrsRuntimeException extends RuntimeException {

    private UserMessage userMessage;

    public CbrsRuntimeException() {
        this.userMessage = UserMessage.SEVERE_SYSTEM_ERROR;
    }

    public CbrsRuntimeException(UserMessage userMessage){
        this.userMessage = userMessage;
    }


    public UserMessage getUserMessage() {
        return userMessage;
    }
}
