package com.dataart.citybikerentalservicespring.exceptions.jsonwebtokenexceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by mkrasowski on 20.10.2016.
 */
public class JsonWebTokenException extends AuthenticationException {
    public JsonWebTokenException(Exception ex) {
        super(ex.getMessage());
    }
}
