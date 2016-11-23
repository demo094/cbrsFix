package com.dataart.citybikerentalservicespring.view.controller.mainpage;

import com.dataart.citybikerentalservicespring.exceptions.CbrsException;
import com.dataart.citybikerentalservicespring.exceptions.CbrsRuntimeException;
import com.dataart.citybikerentalservicespring.view.TO.ApiErrorTO;
import com.dataart.citybikerentalservicespring.view.TO.ValidationErrorTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ValidationErrorTO processValidationError(MethodArgumentNotValidException ex) {
        LOGGER.error("Validation problem occured", ex);
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return processFieldValidationErrors(fieldErrors);
    }

    private ValidationErrorTO processFieldValidationErrors(List<FieldError> fieldErrors) {
        ValidationErrorTO errorTO = new ValidationErrorTO();
        for (FieldError fieldError : fieldErrors){
            String localizedErrorMessage = resolveValidationErrorMessage(fieldError);
            errorTO.addFieldError(fieldError.getField(), localizedErrorMessage);
        }
        return errorTO;
    }

    private String resolveValidationErrorMessage(FieldError fieldError) {
        return fieldError.getDefaultMessage();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiErrorTO handleRuntimeExceptions(RuntimeException ex) {
        LOGGER.error("An error occurred!", ex);
        return new ApiErrorTO(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Severe system problem occurred. Please contact with administation.");
    }

}
