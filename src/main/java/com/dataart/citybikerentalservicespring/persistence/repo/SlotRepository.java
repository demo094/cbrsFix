package com.dataart.citybikerentalservicespring.persistence.repo;

import com.dataart.citybikerentalservicespring.persistence.model.Bike;
import com.dataart.citybikerentalservicespring.persistence.model.Slot;
import com.dataart.citybikerentalservicespring.persistence.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by mkrasowski on 04.10.2016.
 */
@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {
    Slot findSlotByBike(Bike bike);

    @Query(value = "delete from Slot where id = :slotId and station = :station")
    void deleteByIdFromStation(@Param("slotId") Integer slotId, @Param("station") Station station);
}
