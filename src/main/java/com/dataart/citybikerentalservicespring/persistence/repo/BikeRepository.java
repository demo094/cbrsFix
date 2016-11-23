package com.dataart.citybikerentalservicespring.persistence.repo;

import com.dataart.citybikerentalservicespring.persistence.model.Bike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


/**
 * Created by mkrasowski on 04.10.2016.
 */
@Repository
public interface BikeRepository extends JpaRepository<Bike, Integer> {

    @Query(value = "select bike from Slot s where s.id = :slotId")
    Bike findBikeBySlotId(@Param("slotId") Integer slotId);
}
