package com.example.taxi_app.controllers;

import com.example.taxi_app.models.Location;
import com.example.taxi_app.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/location")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("{title}")
    public Location getLocationByTitle(@PathVariable(value = "title") String title){
        return locationService.getByTitle(title);
    }

    @PostMapping
    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
        Location saved_location = locationService.add(location);
        return new ResponseEntity<>(saved_location, HttpStatus.CREATED);
    }

}