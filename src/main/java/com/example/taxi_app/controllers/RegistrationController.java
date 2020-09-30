package com.example.taxi_app.controllers;

import com.example.taxi_app.exceptions.InvalidCarNumber;
import com.example.taxi_app.models.Driver;
import com.example.taxi_app.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    private final RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @PostMapping("driver")
    public ResponseEntity<HashMap<String, String>> register(@RequestBody Driver driver) {
        HashMap<String, String> response = new HashMap<>();
        try {
            registrationService.registerDriver(driver);
            response.put("message", "Driver has been successfully registered");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (InvalidCarNumber e) {
            response.put("message", e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        catch (DataIntegrityViolationException e) {
            response.put("message", "User already exists");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
