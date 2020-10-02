package com.example.taxi_app.controllers;

import com.example.taxi_app.models.User;
import com.example.taxi_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;


@RestController
@RequestMapping("api/v1/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public Iterable<User> getAll(HttpServletRequest request) {
        return userService.getAll();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(HttpServletRequest request, @PathVariable(value = "id") UUID id ) {
        userService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
