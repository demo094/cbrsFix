package com.dataart.citybikerentalservicespring.persistence.model;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by mkrasowski on 20.09.2016.
 */

@Entity
@Table(name = "price_interval")
public class PriceInterval {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
//    @Column(nullable = false)
    private long start;
    @Column
    private Long end;
    @Column(nullable = false)
    private BigDecimal price;

    public PriceInterval() {
    }

    public PriceInterval(long start, BigDecimal price) {
        this.start = start;
        this.price = price;
    }

    public PriceInterval(long start, Long end, BigDecimal price) {
        this.start = start;
        this.end = end;
        this.price = price;
    }

    public PriceInterval(Long end, BigDecimal price){
        this.end = end;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
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
