package com.example.taxi_app.services;

import com.example.taxi_app.exceptions.InvalidCarNumber;
import com.example.taxi_app.models.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;


@Service
public class RegistrationService {
    private final DriverService driverDAO;
    private final ValidationService validationService;

    @Autowired
    public RegistrationService(DriverService driverDAO, ValidationService validationService) {
        this.driverDAO = driverDAO;
        this.validationService = validationService;
    }

    public void registerDriver(Driver driver) throws InvalidCarNumber {
        validationService.validateCarNumber(driver.getCarNumber());
        String hashedPassword = BCrypt.hashpw(driver.getPassword(), BCrypt.gensalt(10));
        driver.setPassword(hashedPassword);
        driverDAO.add(driver);
    }

}

