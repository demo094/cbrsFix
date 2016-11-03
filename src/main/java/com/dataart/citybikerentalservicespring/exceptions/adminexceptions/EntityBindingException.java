package com.dataart.citybikerentalservicespring.exceptions.adminexceptions;

import com.dataart.citybikerentalservicespring.constants.UserMessage;
import com.dataart.citybikerentalservicespring.exceptions.CbrsException;
import com.dataart.citybikerentalservicespring.exceptions.CbrsRuntimeException;

/**
 * Created by mkrasowski on 27.09.2016.
 */
public class EntityBindingException extends CbrsRuntimeException {
    public EntityBindingException() {
        super(UserMessage.ENTITY_BINDING);
    }
}
