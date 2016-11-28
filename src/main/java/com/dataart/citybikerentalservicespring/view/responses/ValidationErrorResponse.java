package com.dataart.citybikerentalservicespring.view.responses;

import com.dataart.citybikerentalservicespring.view.TO.FieldErrorTO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mkrasowski on 22.11.2016.
 */
public class ValidationErrorResponse {
    private Map<String, FieldErrorTO> fieldErrors = new HashMap<>();

    public ValidationErrorResponse() {
    }

    public void addFieldError(String field, String message) {
        FieldErrorTO error = new FieldErrorTO(message);
        fieldErrors.put(field, error);
    }

    public Map<String, FieldErrorTO> getFieldErrors() {
        return fieldErrors;
    }
}
