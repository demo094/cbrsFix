package com.dataart.citybikerentalservicespring.exceptions.jwtexceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by mkrasowski on 20.10.2016.
 */
public class JwtTokenException extends AuthenticationException {
    public JwtTokenException(Exception ex) {
        super(ex.getMessage());
    }
}
