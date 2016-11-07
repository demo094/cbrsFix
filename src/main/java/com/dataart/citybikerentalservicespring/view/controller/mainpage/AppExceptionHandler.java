package com.dataart.citybikerentalservicespring.view.controller.mainpage;

import com.dataart.citybikerentalservicespring.exceptions.CbrsException;
import com.dataart.citybikerentalservicespring.exceptions.CbrsRuntimeException;
import com.dataart.citybikerentalservicespring.view.TO.ApiErrorTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by mkrasowski on 07.09.2016.
 */
@RestControllerAdvice
public class AppExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(CbrsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorTO handleCheckedExceptions(CbrsException ex) {
        LOGGER.error("There was some problem on the site.", ex);
        return new ApiErrorTO(HttpStatus.BAD_REQUEST.value(), ex.getUserMessage().getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ApiErrorTO handleAuthenticationExceptions(AuthenticationException ex) {
        LOGGER.error("There was an authentication problem!", ex);
        return new ApiErrorTO(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(CbrsRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrorTO handleCbrsRuntimeException(CbrsRuntimeException ex) {
        LOGGER.error("An error occurred!", ex);
        return new ApiErrorTO(HttpStatus.BAD_REQUEST.value(), (ex.getUserMessage().getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorTO handleRuntimeExceptions(RuntimeException ex) {
        LOGGER.error("An error occurred!", ex);
        return new ApiErrorTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Severe system problem occurred. Please contact with administation.");
    }

}
