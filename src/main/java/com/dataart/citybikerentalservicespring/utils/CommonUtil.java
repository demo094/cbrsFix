package com.dataart.citybikerentalservicespring.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

/**
 * Created by mkrasowski on 30.11.2016.
 */
public class CommonUtil {

    public static Instant parseToInstantDate(String date, String pattern) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        return simpleDateFormat.parse(date).toInstant();
    }
}
