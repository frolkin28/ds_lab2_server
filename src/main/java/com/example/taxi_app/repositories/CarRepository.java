package com.example.taxi_app.repositories;

import com.example.taxi_app.models.Car;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Repository
public interface CarRepository extends CrudRepository<Car, String> {
    @Transactional
    @Modifying
    @Query("delete from Car c where c.id = ?1")
    void deleteById(UUID id);
}
