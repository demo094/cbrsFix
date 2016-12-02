package com.dataart.citybikerentalservicespring.components.security;

import com.dataart.citybikerentalservicespring.exceptions.jsonwebtokenexceptions.JsonWebTokenExpiredException;
import com.dataart.citybikerentalservicespring.exceptions.jsonwebtokenexceptions.JsonWebTokenMissingException;
import com.dataart.citybikerentalservicespring.view.TO.UserDetailsTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mkrasowski on 29.11.2016.
 */

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private JwtHelper jwtHelper;
    @Value("${tokenLifeTime}")
    private Long tokenLifeTime;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
        UserDetailsTO userDetailsTO = jwtHelper.parseToken(jwtAuthenticationToken.getToken());
        if (userDetailsTO == null) {
            throw new JsonWebTokenMissingException("No token found");
        }

        if (Instant.now().isAfter(userDetailsTO.getTokenIssueTime().plusMillis(tokenLifeTime))) {
            throw new JsonWebTokenExpiredException("Token has expired");
        }

        List<GrantedAuthority> authorities = getGrantedAuthorities(userDetailsTO);
        return new UsernamePasswordAuthenticationToken(
                new AuthenticatedUser(userDetailsTO.getId(), userDetailsTO.getEmail(), authorities),
                userDetailsTO.getEmail(), authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }

    private List<GrantedAuthority> getGrantedAuthorities(UserDetailsTO userDetailsTO) {
        return userDetailsTO.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
