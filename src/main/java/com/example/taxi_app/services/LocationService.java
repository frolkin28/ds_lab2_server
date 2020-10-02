package com.example.taxi_app.services;

import com.example.taxi_app.models.Location;
import com.example.taxi_app.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class LocationService {

    private LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location add(Location location) {
        return locationRepository.save(location);
    }

    public Location getByTitle(String title) {
        return locationRepository.findByTitleContainingIgnoreCase(title);
    }

}
