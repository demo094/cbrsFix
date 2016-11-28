package com.dataart.citybikerentalservicespring.components.security;

import com.dataart.citybikerentalservicespring.view.responses.AuthenticationErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by mkrasowski on 31.10.2016.
 */
public class JsonWebTokenAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonWebTokenAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        LOGGER.error("Authentication failure!", exception);
        sendError(response, "Authentication failed!", exception);
    }

    private void sendError(HttpServletResponse response, String message, Exception ex) throws IOException {
        SecurityContextHolder.clearContext();
        AuthenticationErrorResponse authenticationErrorResponse = new AuthenticationErrorResponse(message, ex);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().format("{\"message\":\"%s\", \"cause\":\"%s\"}", authenticationErrorResponse.getMessage(), authenticationErrorResponse.getException().getMessage());
        response.getWriter().flush();
        response.getWriter().close();
    }
}
