
package com.dataart.citybikerentalservicespring.exceptions.userexceptions;

import com.dataart.citybikerentalservicespring.constants.UserMessage;
import com.dataart.citybikerentalservicespring.exceptions.CbrsException;

/**
 * Gets thrown when user tries TO register TO the database, but the username
 * provided is already existing.
 * 
 * @author mkrasowski
 */
public class EmailDuplicationException extends CbrsException {

    public EmailDuplicationException() {
        super(UserMessage.DUPLICATE_EMAIL);
    }
}
