package com.example.taxi_app.repositories;

import com.example.taxi_app.models.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface LocationRepository extends CrudRepository<Location, String> {

    Location findByTitleContainingIgnoreCase(String title);
}
