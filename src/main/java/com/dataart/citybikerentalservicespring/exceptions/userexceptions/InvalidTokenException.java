package com.dataart.citybikerentalservicespring.exceptions.userexceptions;

import com.dataart.citybikerentalservicespring.constants.UserMessage;
import com.dataart.citybikerentalservicespring.exceptions.CbrsException;

/**
 * Created by mkrasowski on 15.09.2016.
 */
public class InvalidTokenException extends CbrsException {
    public InvalidTokenException() {
        super(UserMessage.INVALID_ACTIVATION_TOKEN);
    }
}
