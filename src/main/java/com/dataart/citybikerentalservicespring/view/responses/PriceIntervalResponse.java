package com.dataart.citybikerentalservicespring.view.responses;

import com.dataart.citybikerentalservicespring.persistence.model.PriceInterval;

import java.math.BigDecimal;

/**
 * Created by mkrasowski on 26.09.2016.
 */
public class PriceIntervalResponse {

    private int id;
    private Long end;
    private BigDecimal price;

    public PriceIntervalResponse(PriceInterval priceInterval) {
        this.id = priceInterval.getId();
        this.end = priceInterval.getEnd();
        this.price = priceInterval.getPrice();
    }

    public int getId() {
        return id;
    }

    public Long getEnd() {
        return end;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
