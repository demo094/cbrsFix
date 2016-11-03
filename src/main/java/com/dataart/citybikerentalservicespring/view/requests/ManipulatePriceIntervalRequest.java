package com.dataart.citybikerentalservicespring.view.requests;

import java.math.BigDecimal;

/**
 * Created by mkrasowski on 26.09.2016.
 */
public class ManipulatePriceIntervalRequest {
    private int id;
    private Long start;
    private Long end;
    private BigDecimal price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getStart() {
        return start;
    }

    public void setStart(Long start) {
        this.start = start;
    }

    public Long getEnd() {
        return end;
    }

    public void setEnd(Long end) {
        this.end = end;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
