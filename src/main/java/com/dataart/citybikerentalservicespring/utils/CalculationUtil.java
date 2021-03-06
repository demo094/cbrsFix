package com.dataart.citybikerentalservicespring.utils;

import com.dataart.citybikerentalservicespring.persistence.model.PriceInterval;
import com.dataart.citybikerentalservicespring.view.responses.PriceIntervalResponse;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by mkrasowski on 22.09.2016.
 */
public class CalculationUtil {
    private static final int ONE_MINUTE = 60;

    public static BigDecimal calculateTripPrice(long seconds, List<PriceIntervalResponse> priceIntervalList) {
        BigDecimal tripPrice = BigDecimal.ZERO;
        long startInterval = 0L;

        for (PriceIntervalResponse priceInterval : priceIntervalList) {
            if (priceInterval.getEnd() != null && seconds >= startInterval
                    && (seconds <= priceInterval.getEnd() || seconds >= priceInterval.getEnd())){
                startInterval = priceInterval.getEnd();
                tripPrice = tripPrice.add(priceInterval.getPrice());
            }
        }

        long lastIntervalStart = startInterval;
        if (seconds >= lastIntervalStart) {
            PriceIntervalResponse lastPriceInterval = priceIntervalList.get(priceIntervalList.size() - 1);
            tripPrice = tripPrice.add(lastPriceInterval.getPrice().multiply(BigDecimal.valueOf(((seconds - lastIntervalStart) / ONE_MINUTE) + 1)));
        }

        return tripPrice;
    }
}
