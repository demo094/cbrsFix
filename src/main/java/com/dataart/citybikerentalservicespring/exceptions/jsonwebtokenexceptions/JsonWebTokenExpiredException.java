package com.dataart.citybikerentalservicespring.exceptions.jsonwebtokenexceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by mkrasowski on 19.10.2016.
 */
public class JsonWebTokenExpiredException extends AuthenticationException {
    public JsonWebTokenExpiredException(String s) {
        super(s);
    }
}
