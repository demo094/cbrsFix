package com.dataart.citybikerentalservicespring.exceptions.adminexceptions;

import com.dataart.citybikerentalservicespring.constants.UserMessage;
import com.dataart.citybikerentalservicespring.exceptions.CbrsException;

/**
 * Created by mkrasowski on 30.09.2016.
 */
public class PriceIntervalNotFoundException extends CbrsException {
    public PriceIntervalNotFoundException() {
        super(UserMessage.NO_PRICE_INTERVAL);
    }
}
