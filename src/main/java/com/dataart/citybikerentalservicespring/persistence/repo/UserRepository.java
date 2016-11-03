package com.dataart.citybikerentalservicespring.persistence.repo;

import com.dataart.citybikerentalservicespring.persistence.model.Bike;
import com.dataart.citybikerentalservicespring.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by mkrasowski on 04.10.2016.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "from User u join Token t on u.id = t.user where t.body = :body")
    User findByToken(@Param("body") String tokenBody);

    @Query(value = "from User u join fetch u.userRoleList where u.email = :email")
    User findByEmail(@Param("email") String email);

    @Query(value = "select passwordHash from User where email = :email")
    String findPasswordHash(@Param("email") String email);

    @Query(value = "select bike from User u where u.id = :userId")
    Bike findRentedBike(@Param("userId") Integer userId);

    @Modifying
    @Query(value = "update User set activated = :activated where id = :userId")
    void setActivated(@Param("activated") boolean activated, @Param("userId") Integer userId);

    @Modifying
    @Query(value = "update User set balance = balance - :amount where id = :userId")
    void substractMoney(@Param("amount") BigDecimal amount, @Param("userId") Integer userId);

    @Modifying
    @Query(value = "update User set balance = balance + :amount where id = :userId")
    void addMoney(@Param("amount") BigDecimal amount, @Param("userId") Integer userId);
}
