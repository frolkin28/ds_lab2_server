package com.example.taxi_app.services;

import com.example.taxi_app.models.Driver;
import com.example.taxi_app.repositories.DriverRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DriverService {
    @Autowired
    public DriverRepository driverRepository;

    public Iterable<Driver> getAll() {
        return driverRepository.findAll();
    }

    public void add(Driver driver) {
        driverRepository.save(driver);
    }

    public void remove(UUID id) {
        driverRepository.deleteById(id);
    }

    public Driver getByCarNumber(String carNumber) {
        return driverRepository.findByCarNumber(carNumber);
    }

    public Driver validate(String car_number, String password) {
        Driver driver = driverRepository.findByCarNumber(car_number);
        if (driver != null && BCrypt.checkpw(password, driver.getPassword()))
            return driver;
        else
            return null;
    }
}
