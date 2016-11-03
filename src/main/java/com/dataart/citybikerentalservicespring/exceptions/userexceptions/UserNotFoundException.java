package com.dataart.citybikerentalservicespring.exceptions.userexceptions;

import com.dataart.citybikerentalservicespring.constants.UserMessage;
import com.dataart.citybikerentalservicespring.exceptions.CbrsException;
import org.springframework.security.core.AuthenticationException;

/**
 * Created by mkrasowski on 28.09.2016.
 */
public class UserNotFoundException extends CbrsException {

    public UserNotFoundException() {
         super(UserMessage.USER_NOT_FOUND);
    }

}
