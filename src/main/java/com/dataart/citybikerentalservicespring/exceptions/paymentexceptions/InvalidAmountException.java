package com.dataart.citybikerentalservicespring.exceptions.paymentexceptions;

import com.dataart.citybikerentalservicespring.constants.UserMessage;
import com.dataart.citybikerentalservicespring.exceptions.CbrsException;

/**
 * Created by mkrasowski on 26.09.2016.
 */
public class InvalidAmountException extends CbrsException {
    public InvalidAmountException() {
        super(UserMessage.INVALID_AMOUNT_INPUT);
    }
}
