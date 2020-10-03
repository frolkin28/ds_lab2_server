package com.example.taxi_app.controllers;

import com.example.taxi_app.models.User;
import com.example.taxi_app.services.UserService;
import com.example.taxi_app.services.TokenService;
import com.example.taxi_app.services.enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("login")
public class LoginController {

    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public LoginController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity<HashMap<String, String>> login(@RequestBody Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        User validatedUser = userService.validate(email, password);
        HashMap<String, String> response = new HashMap<>();

        if (validatedUser != null) {
            Role role = validatedUser.getRole();
            String token = tokenService.generateJWTToken(validatedUser.getEmail(), role);
            response.put("token", token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            response.put("message", "invalid credentials");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }
}
