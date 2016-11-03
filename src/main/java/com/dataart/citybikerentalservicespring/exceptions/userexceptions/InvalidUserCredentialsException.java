
package com.dataart.citybikerentalservicespring.exceptions.userexceptions;

import com.dataart.citybikerentalservicespring.constants.UserMessage;
import com.dataart.citybikerentalservicespring.exceptions.CbrsException;

/**
 * Gets thrown when user provided invalid credentials(either on the logging or the registration).
 * 
 * @author mkrasowski
 */
public class InvalidUserCredentialsException extends CbrsException {

    public InvalidUserCredentialsException() {
        super(UserMessage.INVALID_USER_CREDENTIALS);
    }
    
}
