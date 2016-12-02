package com.dataart.citybikerentalservicespring.persistence.repo;

import com.dataart.citybikerentalservicespring.persistence.model.PriceInterval;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;

/**
 * Created by mkrasowski on 04.10.2016.
 */
@Repository
public interface PriceIntervalRepository extends JpaRepository<PriceInterval, Integer> {
    @Lock(LockModeType.PESSIMISTIC_READ)
    List<PriceInterval> findAllByOrderByEndAsc();
}
