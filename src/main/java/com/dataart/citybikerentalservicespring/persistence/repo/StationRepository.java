package com.dataart.citybikerentalservicespring.persistence.repo;

import com.dataart.citybikerentalservicespring.persistence.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkrasowski on 04.10.2016.
 */

@Repository
public interface StationRepository extends JpaRepository<Station, Integer> {

    @Query(value = "from Station s left join fetch s.slotList where s.id = :stationId")
    Station findById(@Param("stationId") Integer id);
}
