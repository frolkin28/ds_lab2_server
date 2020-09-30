package com.example.taxi_app.controllers;

import com.example.taxi_app.models.Driver;
import com.example.taxi_app.services.DriverService;
import com.example.taxi_app.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("login")
public class LoginController {

    private final DriverService driverService;
    private final TokenService tokenService;

    @Autowired
    public LoginController(DriverService driverService, TokenService tokenService) {
        this.driverService = driverService;
        this.tokenService = tokenService;
    }

    @GetMapping("driver")
    public ResponseEntity<HashMap<String, String>> loginDriver(@RequestBody Map<String, Object> userMap) {
        String car_number = (String) userMap.get("carNumber");
        String password = (String) userMap.get("password");

        Driver validatedDriver = driverService.validate(car_number, password);
        HashMap<String, String> response = new HashMap<>();

        if (validatedDriver != null) {
            String token = tokenService.generateJWTToken(validatedDriver.getCarNumber());
            response.put("token", token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            response.put("message", "invalid credentials");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
