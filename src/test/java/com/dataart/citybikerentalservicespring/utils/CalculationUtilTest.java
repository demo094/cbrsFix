package com.dataart.citybikerentalservicespring.utils;

import com.dataart.citybikerentalservicespring.persistence.model.PriceInterval;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by mkrasowski on 20.09.2016.
 */
public class CalculationUtilTest {

    @Test
    public void calculateTripPrice() {
//

        assertEquals(BigDecimal.ZERO, CalculationUtil.calculateTripPrice(17, getPriceIntervalList()));
        assertEquals(BigDecimal.ONE, CalculationUtil.calculateTripPrice(20, getPriceIntervalList()));
        assertEquals(BigDecimal.ONE, CalculationUtil.calculateTripPrice(24, getPriceIntervalList()));
        assertEquals(new BigDecimal(3), CalculationUtil.calculateTripPrice(60, getPriceIntervalList()));
        assertEquals(new BigDecimal(3), CalculationUtil.calculateTripPrice(100, getPriceIntervalList()));
        assertEquals(new BigDecimal(6), CalculationUtil.calculateTripPrice(120, getPriceIntervalList()));
        assertEquals(new BigDecimal(6), CalculationUtil.calculateTripPrice(140, getPriceIntervalList()));

    }


    public List<PriceInterval> getPriceIntervalList() {
        List<PriceInterval> priceIntervalList = new ArrayList<>();
        priceIntervalList.add(new PriceInterval(20L, BigDecimal.ZERO));
        priceIntervalList.add(new PriceInterval(60L, BigDecimal.ONE));
        priceIntervalList.add(new PriceInterval(120L, BigDecimal.valueOf(2)));
        priceIntervalList.add(new PriceInterval(null, BigDecimal.valueOf(3)));
        return priceIntervalList;
    }
}
