package com.example.taxi_app.repositories;

import com.example.taxi_app.models.Driver;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface DriverRepository extends CrudRepository<Driver, String> {
    @Transactional
    @Modifying
    @Query("delete from Driver d where d.id = ?1")
    void deleteById(UUID id);

    @Query("select d from Driver d where d.carNumber='?1'")
    Driver findByCarNumber(String carNumber);

}
