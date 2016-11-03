package com.dataart.citybikerentalservicespring.persistence.repo;

import com.dataart.citybikerentalservicespring.persistence.model.Payment;
import com.dataart.citybikerentalservicespring.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mkrasowski on 04.10.2016.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Payment findFirstByUserOrderByIdDesc(User user);
}
