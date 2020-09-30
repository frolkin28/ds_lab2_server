package com.example.taxi_app.exceptions;

public class InvalidEmail extends Exception {
    public InvalidEmail(String message) {
        super(message);
    }
}