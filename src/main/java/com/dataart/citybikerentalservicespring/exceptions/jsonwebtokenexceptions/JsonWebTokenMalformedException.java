package com.dataart.citybikerentalservicespring.exceptions.jsonwebtokenexceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by mkrasowski on 18.10.2016.
 */
public class JsonWebTokenMalformedException extends AuthenticationException {
    public JsonWebTokenMalformedException(String s) {
        super(s);
    }
}
