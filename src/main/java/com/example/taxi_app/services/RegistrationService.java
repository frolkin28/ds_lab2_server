package com.example.taxi_app.services;

import com.example.taxi_app.exceptions.InvalidEmail;
import com.example.taxi_app.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.mindrot.jbcrypt.BCrypt;


@Service
public class RegistrationService {
    private final ValidationService validationService;
    private final UserService userService;

    @Autowired
    public RegistrationService(ValidationService validationService, UserService userService) {
        this.validationService = validationService;
        this.userService = userService;
    }

    public void registerUser(User user) throws InvalidEmail {
        validationService.validateEmail(user.getEmail());
        String hashedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(10));
        user.setPassword(hashedPassword);
        userService.add(user);
    }

}

