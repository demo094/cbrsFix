package com.dataart.citybikerentalservicespring.view.TO;

import com.dataart.citybikerentalservicespring.persistence.model.PriceInterval;

import java.math.BigDecimal;

/**
 * Created by mkrasowski on 26.09.2016.
 */
public class PriceIntervalTO {

    private int id;
    private Long start;
    private Long end;
    private BigDecimal price;

    public PriceIntervalTO(PriceInterval priceInterval) {
        this.id = priceInterval.getId();
        this.start = priceInterval.getStart();
        this.end = priceInterval.getEnd();
        this.price = priceInterval.getPrice();
    }

    public int getId() {
        return id;
    }

    public Long getStart() {
        return start;
    }

    public Long getEnd() {
        return end;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
