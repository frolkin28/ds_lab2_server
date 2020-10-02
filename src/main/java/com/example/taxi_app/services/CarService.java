package com.example.taxi_app.services;

import com.example.taxi_app.models.Car;
import com.example.taxi_app.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarService {
    private CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car add(Car car) {
        return carRepository.save(car);
    }

    public Iterable<Car> getAll() {
        return carRepository.findAll();
    }

    public void remove(UUID id) {
        carRepository.deleteById(id);
    }
}
