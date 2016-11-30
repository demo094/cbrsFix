package com.dataart.citybikerentalservicespring.utils.annotations;

import com.dataart.citybikerentalservicespring.utils.CommonUtil;
import org.springframework.format.datetime.standard.InstantFormatter;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Formatter;

/**
 * Created by mkrasowski on 30.11.2016.
 */
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotExpired.ExpirationDateValidator.class)
public @interface NotExpired {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


    class ExpirationDateValidator implements ConstraintValidator<NotExpired, String> {
        @Override
        public void initialize(NotExpired constraintAnnotation) {
        }

        @Override
        public boolean isValid(String date, ConstraintValidatorContext context) {
            try {
                return CommonUtil.parseToInstantDate(date, "MM-yyyy").isAfter(Instant.now());
            } catch (ParseException e) {
                return false;
            }
        }
    }
}
