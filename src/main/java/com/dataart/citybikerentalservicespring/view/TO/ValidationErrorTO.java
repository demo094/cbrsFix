package com.dataart.citybikerentalservicespring.view.TO;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mkrasowski on 22.11.2016.
 */
public class ValidationErrorTO {
    private Map<String, FieldErrorTO> fieldErrors = new HashMap<>();

    public ValidationErrorTO() {
    }

    public void addFieldError(String field, String message) {
        FieldErrorTO error = new FieldErrorTO(message);
        fieldErrors.put(field, error);
    }

    public Map<String, FieldErrorTO> getFieldErrors() {
        return fieldErrors;
    }
}
