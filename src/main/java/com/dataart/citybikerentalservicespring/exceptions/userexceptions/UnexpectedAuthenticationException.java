package com.dataart.citybikerentalservicespring.exceptions.userexceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by mkrasowski on 11.10.2016.
 */
public class UnexpectedAuthenticationException extends AuthenticationException {
    public UnexpectedAuthenticationException(Throwable t) {
        super("Some unexpected system error occurred", t);
    }
}
