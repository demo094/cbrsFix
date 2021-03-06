
package com.dataart.citybikerentalservicespring.utils;

import com.dataart.citybikerentalservicespring.constants.UserMessage;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;


public class WebUtil {

    public static String createActivationLink(String token) {
//        remember to create some configuration
        String applicationURL = "http://localhost:8080/citybikerentalservicespring";
        return String.format("%s/#/main/registrationconfirm/%s", applicationURL, token);
    }

    public static Date calculateTokenExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.HOUR, 24);
        return new Date(cal.getTime().getTime());
    }

    public static int calculateSecPassed(Instant date) {
        return (int) Duration.between(date, Instant.now()).toMillis() / 1000;
    }

    public static String createPasswordResetLink(String tokenBody) {
        String applicationURL = "http://localhost:8080/citybikerentalservicespring";
        return String.format("%s/#/main/resetpasswordpage/%s", applicationURL, tokenBody);
    }
}
