package com.dataart.citybikerentalservicespring.exceptions.userexceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by mkrasowski on 14.09.2016.
 */
public class AccountNotActivatedException extends AuthenticationException {
    public AccountNotActivatedException() {
        super("Account is not activated. Please use the link sent to you in the e-mail.");
    }
}
