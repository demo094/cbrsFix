package com.dataart.citybikerentalservicespring.persistence.model;

import javax.persistence.*;

/**
 * Created by mkrasowski on 06.09.2016.
 */

@Entity
@Table(name = "bike")
public class Bike {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column
    private String type;

    public Bike() {
    }

    public Bike(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
