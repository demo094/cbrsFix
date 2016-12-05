package com.dataart.citybikerentalservicespring.components.security;

import com.dataart.citybikerentalservicespring.view.responses.AuthenticationErrorResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private static final Logger LOGGER = LogManager.getLogger(RestUnauthorizedEntryPoint.class);

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        LOGGER.error("!", "Somebody tried to access the restricted services without authentication.");
        sendError(response, "Insufficient permission rights! Maybe you are not logged in or tried to access restricted services.", authException);
    }

    private void sendError(HttpServletResponse response, String message, Exception exception) throws IOException {
        SecurityContextHolder.clearContext();
        AuthenticationErrorResponse authenticationErrorResponse = new AuthenticationErrorResponse(message, exception);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().format("{\"message\":\"%s\"}", authenticationErrorResponse.getMessage());
        response.getWriter().flush();
        response.getWriter().close();
    }
}
