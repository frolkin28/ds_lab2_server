package com.example.taxi_app.controllers;

import com.example.taxi_app.models.Car;
import com.example.taxi_app.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1/car")
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public Iterable<Car> getAll() {
        return carService.getAll();
    }

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car saved_car = carService.add(car);
        return new ResponseEntity<>(saved_car, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable(value = "id") UUID id ) {
        carService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}