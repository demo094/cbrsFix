package com.dataart.citybikerentalservicespring.persistence.repo;

import com.dataart.citybikerentalservicespring.persistence.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by mkrasowski on 04.10.2016.
 */

@Repository
public interface TokenRepository extends JpaRepository<Token, Integer>{

    @Query(value = "from Token where body = :body")
    Token findByBody(@Param("body") String body);
}
