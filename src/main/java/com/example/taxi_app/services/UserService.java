package com.example.taxi_app.services;

import com.example.taxi_app.models.User;
import com.example.taxi_app.repositories.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }

    public void add(User driver) {
        userRepository.save(driver);
    }

    public void remove(UUID id) {
        userRepository.deleteById(id);
    }

    public User validate(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && BCrypt.checkpw(password, user.getPassword()))
            return user;
        else
            return null;
    }
}
