package com.dataart.citybikerentalservicespring.view.controller.mainpage;

import com.dataart.citybikerentalservicespring.exceptions.CbrsException;
import com.dataart.citybikerentalservicespring.exceptions.CbrsRuntimeException;
import com.dataart.citybikerentalservicespring.view.responses.StatusErrorResponse;
import com.dataart.citybikerentalservicespring.view.responses.ValidationErrorResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * Created by mkrasowski on 07.09.2016.
 */
@RestControllerAdvice
public class AppExceptionHandler {

    private MessageSource messageSource;

    @Autowired
    public AppExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private static final Logger LOGGER = LogManager.getLogger(AppExceptionHandler.class);

    @ExceptionHandler(CbrsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StatusErrorResponse handleCheckedExceptions(CbrsException ex) {
        LOGGER.error("There was some problem on the site.", ex);
        return new StatusErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getUserMessage().getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public StatusErrorResponse handleAuthenticationExceptions(AuthenticationException ex) {
        LOGGER.error("There was an authentication problem!", ex);
        return new StatusErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public StatusErrorResponse handleAccessDeniedException(AccessDeniedException ex){
        LOGGER.error("Somebody tried to access resources without sufficient permission!", ex);
        return new StatusErrorResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
    }

    @ExceptionHandler(CbrsRuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public StatusErrorResponse handleCbrsRuntimeException(CbrsRuntimeException ex) {
        LOGGER.error("An error occurred!", ex);
        return new StatusErrorResponse(HttpStatus.BAD_REQUEST.value(), (ex.getUserMessage().getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorResponse processValidationError(MethodArgumentNotValidException ex) {
        LOGGER.error("Validation problem occured", ex);
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return processFieldValidationErrors(fieldErrors);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public StatusErrorResponse handleRuntimeExceptions(RuntimeException ex) {
        LOGGER.error("An error occurred!", ex);
        return new StatusErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Severe system problem occurred. Please contact with administation.");
    }

    private ValidationErrorResponse processFieldValidationErrors(List<FieldError> fieldErrors) {
        ValidationErrorResponse errorTO = new ValidationErrorResponse();
        for (FieldError fieldError : fieldErrors){
            String localizedErrorMessage = resolveValidationErrorMessage(fieldError);
            errorTO.addFieldError(fieldError.getField(), localizedErrorMessage);
        }
        return errorTO;
    }

    private String resolveValidationErrorMessage(FieldError fieldError) {
        return fieldError.getDefaultMessage();
    }

}
