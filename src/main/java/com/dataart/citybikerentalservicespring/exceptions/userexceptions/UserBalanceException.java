package com.dataart.citybikerentalservicespring.exceptions.userexceptions;

import com.dataart.citybikerentalservicespring.constants.UserMessage;
import com.dataart.citybikerentalservicespring.exceptions.CbrsException;

/**
 * Created by mkrasowski on 16.09.2016.
 */
public class UserBalanceException extends CbrsException {
    public UserBalanceException() {
        super(UserMessage.USER_BALANCE_ERROR);
    }
}
