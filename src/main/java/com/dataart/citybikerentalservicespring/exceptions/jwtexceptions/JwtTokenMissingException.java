package com.dataart.citybikerentalservicespring.exceptions.jwtexceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by mkrasowski on 18.10.2016.
 */
public class JwtTokenMissingException extends AuthenticationException {
    public JwtTokenMissingException(String s) {
        super(s);

    }
}
