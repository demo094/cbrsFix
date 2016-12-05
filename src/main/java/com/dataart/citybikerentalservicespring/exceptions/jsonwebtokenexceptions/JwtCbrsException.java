package com.dataart.citybikerentalservicespring.exceptions.jsonwebtokenexceptions;

import com.dataart.citybikerentalservicespring.exceptions.CbrsAuthenticationException;

/**
 * Created by mkrasowski on 02.12.2016.
 */
public class JwtCbrsException extends CbrsAuthenticationException {
    public JwtCbrsException(String s) {
        super(s);
    }
}
