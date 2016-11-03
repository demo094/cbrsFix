package com.dataart.citybikerentalservicespring.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by mkrasowski on 28.10.2016.
 */
public class CbrsAuthenticationException extends AuthenticationException {
    public CbrsAuthenticationException(String msg) {
        super(msg);
    }
}
