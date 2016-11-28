package com.dataart.citybikerentalservicespring.components.security;

import com.dataart.citybikerentalservicespring.exceptions.jsonwebtokenexceptions.JsonWebTokenExpiredException;
import com.dataart.citybikerentalservicespring.exceptions.jsonwebtokenexceptions.JsonWebTokenMissingException;
import com.dataart.citybikerentalservicespring.view.TO.UserDetailsTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Created by mkrasowski on 18.10.2016.
 */
@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtHelper jwtHelper;
    @Value("${tokenLifeTime}")
    private long tokenLifeTime;

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        UserDetailsTO userDetailsTO = jwtHelper.parseToken(jwtAuthenticationToken.getToken());
        if (userDetailsTO == null) {
            throw new JsonWebTokenMissingException("No token found");
        }

        if (Instant.now().isAfter(userDetailsTO.getTokenIssueTime().plusMillis(tokenLifeTime))) {
            throw new JsonWebTokenExpiredException("Token has expired");
        }

        List<GrantedAuthority> authorities = getGrantedAuthorities(userDetailsTO);
        return new AuthenticatedUser(userDetailsTO.getId(), userDetailsTO.getEmail(), authorities);
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserDetailsTO userDetailsTO) {
        return userDetailsTO.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}






