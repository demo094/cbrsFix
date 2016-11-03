package com.dataart.citybikerentalservicespring.persistence.repo;

import com.dataart.citybikerentalservicespring.persistence.model.Bike;
import com.dataart.citybikerentalservicespring.persistence.model.RentalHistory;
import com.dataart.citybikerentalservicespring.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.UniqueConstraint;
import java.time.Instant;

/**
 * Created by mkrasowski on 04.10.2016.
 */

@Repository
public interface RentalHistoryRepository extends JpaRepository<RentalHistory, Integer> {

    RentalHistory findFirstByBikeAndUserOrderByIdDesc(Bike bike, User user);

    RentalHistory findFirstByUserOrderByIdDesc(User user);

    @Modifying
    @Query(value = "update rental_history r set r.end_slot_id = :endSlotId, r.end_time = :endTime where r.id = " +
            "(select max(id) from (select * from rental_history where bike_id = :bikeId and user_id = :userId) as id)",
            nativeQuery = true)
    void updateLastRecord(@Param("endSlotId") Integer slotId, @Param("endTime") Instant endTime,
                          @Param("bikeId") Integer bikeId, @Param("userId") Integer userId);
}
