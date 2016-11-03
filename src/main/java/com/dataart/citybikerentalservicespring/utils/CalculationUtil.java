package com.dataart.citybikerentalservicespring.utils;

import com.dataart.citybikerentalservicespring.persistence.model.PriceInterval;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by mkrasowski on 22.09.2016.
 */
@Component
public class CalculationUtil {

    public static BigDecimal calculateTripPrice(long seconds, List<PriceInterval> priceIntervalList) {
        BigDecimal tripPrice = BigDecimal.ZERO;

        for (PriceInterval priceInterval : priceIntervalList) {
            if ((priceInterval.getEnd() == null || priceInterval.getEnd() >= seconds || priceInterval.getEnd() <= seconds)
                    && priceInterval.getStart() <= seconds) {
                tripPrice = tripPrice.add(priceInterval.getPrice());
            }
        }

        return tripPrice;
    }

    public static BigDecimal calculateTripPriceNewAlgorithm(long seconds, List<PriceInterval> priceIntervalList) {
        BigDecimal tripPrice = BigDecimal.ZERO;
        Long notNullIntervalEnd = 0L;

        //TODO new algorithm
        for (PriceInterval priceInterval : priceIntervalList) {
            if (priceInterval.getEnd() != null && (seconds > 0 &&
                    seconds <= priceInterval.getEnd() || seconds >= priceInterval.getEnd())) {
                notNullIntervalEnd = priceInterval.getEnd();
                tripPrice = tripPrice.add(priceInterval.getPrice());
            }

            if (seconds > notNullIntervalEnd && priceInterval.getEnd() == null) {
                tripPrice = tripPrice.add(priceInterval.getPrice().multiply(new BigDecimal((seconds - notNullIntervalEnd) / 60)));
            }
//            if ((priceInterval.getEnd() == null || priceInterval.getEnd() >= seconds || priceInterval.getEnd() <= seconds)
//                    && priceInterval.getStart() <= seconds) {
//
//            }
        }

        return tripPrice;
    }
}
