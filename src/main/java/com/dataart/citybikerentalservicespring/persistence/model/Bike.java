package com.dataart.citybikerentalservicespring.persistence.model;

import com.dataart.citybikerentalservicespring.constants.BikeType;

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
    private BikeType type;

    public Bike() {
    }

    public Bike(Integer id){
        this.id = id;
    }

    public Bike(Integer id, BikeType type){
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Enumerated(EnumType.STRING)
    public BikeType getType() {
        return type;
    }

    public void setType(BikeType type) {
        this.type = type;
    }

}
