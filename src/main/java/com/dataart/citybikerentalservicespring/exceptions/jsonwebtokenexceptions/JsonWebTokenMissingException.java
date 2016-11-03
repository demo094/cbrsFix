package com.dataart.citybikerentalservicespring.exceptions.jsonwebtokenexceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by mkrasowski on 18.10.2016.
 */
public class JsonWebTokenMissingException extends AuthenticationException {
    public JsonWebTokenMissingException(String s) {
        super(s);

    }
}
