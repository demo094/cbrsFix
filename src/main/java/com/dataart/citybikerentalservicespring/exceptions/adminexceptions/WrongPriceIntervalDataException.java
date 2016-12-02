package com.dataart.citybikerentalservicespring.exceptions.adminexceptions;

import com.dataart.citybikerentalservicespring.constants.UserMessage;
import com.dataart.citybikerentalservicespring.exceptions.CbrsRuntimeException;

/**
 * Created by mkrasowski on 01.12.2016.
 */
public class WrongPriceIntervalDataException extends CbrsRuntimeException {
    public WrongPriceIntervalDataException(UserMessage userMessage) {
        super(userMessage);
    }
}
