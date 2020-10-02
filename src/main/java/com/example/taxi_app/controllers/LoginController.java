package com.example.taxi_app.controllers;

import com.example.taxi_app.models.User;
import com.example.taxi_app.services.UserService;
import com.example.taxi_app.services.TokenService;
import com.example.taxi_app.services.enums.Role;
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

    private final UserService userService;
    private final TokenService tokenService;

    @Autowired
    public LoginController(UserService userService, TokenService tokenService) {
        this.userService = userService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<HashMap<String, String>> loginDriver(@RequestBody Map<String, Object> userMap) {
        String email = (String) userMap.get("email");
        String password = (String) userMap.get("password");

        User validatedUser = userService.validate(email, password);
        HashMap<String, String> response = new HashMap<>();
        Role role = validatedUser.getRole();

        if (validatedUser != null) {
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
