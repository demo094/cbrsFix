package com.dataart.citybikerentalservicespring.components.security;

import com.dataart.citybikerentalservicespring.exceptions.CbrsAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mkrasowski on 18.10.2016.
 */
@Component
public class RestUnauthorizedEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        throw new CbrsAuthenticationException("Access to the resource is denied!");
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access to resource denied! It's possible that you have insufficient permission rights.");
    }


}
