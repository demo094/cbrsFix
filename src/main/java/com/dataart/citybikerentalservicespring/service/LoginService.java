package com.dataart.citybikerentalservicespring.service;

import com.dataart.citybikerentalservicespring.components.security.JwtAuthenticationProvider;
import com.dataart.citybikerentalservicespring.components.security.JwtAuthenticationToken;
import com.dataart.citybikerentalservicespring.components.security.JwtHelper;
import com.dataart.citybikerentalservicespring.exceptions.userexceptions.AccountNotActivatedException;
import com.dataart.citybikerentalservicespring.exceptions.userexceptions.UnexpectedAuthenticationException;
import com.dataart.citybikerentalservicespring.persistence.model.User;
import com.dataart.citybikerentalservicespring.view.requests.AuthenticationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by mkrasowski on 28.10.2016.
 */
@Service
public class LoginService {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtHelper jwtHelper;
    @Autowired
    private JwtAuthenticationProvider authenticationProvider;

    public String createAuthenticationToken(AuthenticationRequest authenticationRequest) {
        try {
            String email = authenticationRequest.getEmail();
            String password = authenticationRequest.getPassword();
            if (email == null || password == null || email.isEmpty() || password.isEmpty()) {
                throw new BadCredentialsException("Empty credentials provided.");
            }

            User user = userService.findByEmail(email);

            if (user == null || !user.getEmail().equalsIgnoreCase(email)) {
                throw new BadCredentialsException("Email not found.");
            } else if (!passwordEncoder.matches(password, user.getPasswordHash())) {
                throw new BadCredentialsException("Wrong password.");
            } else if (!user.isActivated()) {
                throw new AccountNotActivatedException();
            } else {
                return jwtHelper.generateToken(user);
            }
        } catch (RuntimeException ex) {
            if (ex instanceof AuthenticationException) {
                throw ex;
            } else {
                throw new UnexpectedAuthenticationException(ex);
            }
        }
    }
}
