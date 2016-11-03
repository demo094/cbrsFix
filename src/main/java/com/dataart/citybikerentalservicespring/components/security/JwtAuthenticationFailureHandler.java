package com.dataart.citybikerentalservicespring.components.security;

import com.dataart.citybikerentalservicespring.view.responses.ErrorResponse;
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
public class JwtAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationFailureHandler.class);

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        LOGGER.error("Authentication failure!", exception);
        sendError(response, "Authentication failed!", exception);
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed!");
    }

    private void sendError(HttpServletResponse response, String message, Exception ex) throws IOException {
        SecurityContextHolder.clearContext();
        ErrorResponse errorResponse = new ErrorResponse(message, ex);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().format("{\"message\":\"%s\", \"cause\":\"%s\"}", errorResponse.getMessage(), errorResponse.getException().getMessage());
        response.getWriter().flush();
        response.getWriter().close();
    }
}
