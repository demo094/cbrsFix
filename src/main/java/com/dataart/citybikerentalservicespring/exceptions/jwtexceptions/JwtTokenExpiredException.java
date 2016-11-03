package com.dataart.citybikerentalservicespring.exceptions.jwtexceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by mkrasowski on 19.10.2016.
 */
public class JwtTokenExpiredException extends AuthenticationException {
    public JwtTokenExpiredException(String s) {
        super(s);
    }
}
