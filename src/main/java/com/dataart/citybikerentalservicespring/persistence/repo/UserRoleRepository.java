package com.dataart.citybikerentalservicespring.persistence.repo;

import com.dataart.citybikerentalservicespring.persistence.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mkrasowski on 04.10.2016.
 */

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    @Query(value = "from UserRole where id = 1")
    UserRole findUserRole();

    @Query(value = "from UserRole where id = 2")
    UserRole getAdminRole();

}
