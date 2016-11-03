package com.dataart.citybikerentalservicespring.exceptions.paymentexceptions;

import com.dataart.citybikerentalservicespring.constants.UserMessage;
import com.dataart.citybikerentalservicespring.exceptions.CbrsException;

/**
 * Created by mkrasowski on 20.09.2016.
 */
public class InvalidCardCredentialsException extends CbrsException {
    public InvalidCardCredentialsException() {
        super(UserMessage.INVALID_CARD_CREDENTIALS);
    }
}
