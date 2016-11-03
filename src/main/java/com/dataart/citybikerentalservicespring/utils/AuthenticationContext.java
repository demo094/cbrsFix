package com.dataart.citybikerentalservicespring.utils;

import com.dataart.citybikerentalservicespring.components.security.AuthenticatedUser;
import com.dataart.citybikerentalservicespring.persistence.model.User;
import com.dataart.citybikerentalservicespring.view.TO.UserDetailsTO;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by mkrasowski on 09.09.2016.
 */

public class AuthenticationContext {

    public static AuthenticatedUser getAuthenticatedUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof AuthenticatedUser){
            return ((AuthenticatedUser) principal);
        }
        return null;
    }
}
