package com.dataart.citybikerentalservicespring.service;

import com.dataart.citybikerentalservicespring.components.security.AuthenticatedUserFactory;
import com.dataart.citybikerentalservicespring.persistence.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by mkrasowski on 28.11.2016.
 */
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);

        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with an email %s.", email));
        } else {
            return AuthenticatedUserFactory.create(user);
        }
    }
}
