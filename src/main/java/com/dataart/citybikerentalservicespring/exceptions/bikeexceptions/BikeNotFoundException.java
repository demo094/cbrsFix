package com.dataart.citybikerentalservicespring.exceptions.bikeexceptions;

import com.dataart.citybikerentalservicespring.constants.UserMessage;
import com.dataart.citybikerentalservicespring.exceptions.CbrsException;

/**
 * Created by mkrasowski on 06.09.2016.
 */
public class BikeNotFoundException extends CbrsException {
    public BikeNotFoundException() {
        super(UserMessage.NO_BIKE_FOUND);
    }
}
