package com.dataart.citybikerentalservicespring.components.security;

import com.dataart.citybikerentalservicespring.persistence.model.User;
import com.dataart.citybikerentalservicespring.persistence.model.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mkrasowski on 28.11.2016.
 */
public class AuthenticatedUserFactory {
    public static UserDetails create(User user) {
        return new AuthenticatedUser(user.getId(), user.getEmail(), getAuthoritiesFromUser(user));
    }

    private static List<GrantedAuthority> getAuthoritiesFromUser(User user){
        return user.getUserRoleList().stream().map(UserRole::getName).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
