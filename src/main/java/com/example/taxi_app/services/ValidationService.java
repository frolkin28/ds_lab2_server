package com.example.taxi_app.services;

import com.example.taxi_app.exceptions.InvalidCarNumber;
import com.example.taxi_app.exceptions.InvalidEmail;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ValidationService {
    private static final String email_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String number_pattern = "^[A-Z]{2}[0-9]{4}[A-Z]{2}$";
    private static final Pattern compiled_email_pattern = Pattern.compile(email_pattern);
    private static final Pattern compiled_number_pattern = Pattern.compile(number_pattern);

    public void validateEmail(String email) throws InvalidEmail {
        Matcher matcher = compiled_email_pattern.matcher(email);
        boolean found = matcher.matches();
        if (!found)
            throw new InvalidEmail("Wrong email");
    }

    public void validateCarNumber(String number) throws InvalidCarNumber {
        Matcher matcher = compiled_number_pattern.matcher(number);
        boolean found = matcher.matches();
        if (!found)
            throw new InvalidCarNumber("Wrong car number");
    }
}