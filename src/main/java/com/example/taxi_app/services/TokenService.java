package com.example.taxi_app.services;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    private final static String API_SECRET_KEY = "my_secret_key";
    private static final long TOKEN_VALIDITY = 2 * 60 * 60 * 1000;

    public String generateJWTToken(String login) {
        long timestamp = System.currentTimeMillis();
        String token = Jwts.builder().signWith(SignatureAlgorithm.HS256, API_SECRET_KEY)
                .setIssuedAt(new Date(timestamp))
                .setExpiration(new Date(timestamp + TOKEN_VALIDITY))
                .claim("login", login)
                .compact();
        return token;
    }

    public static String getApiSecretKey() {
        return API_SECRET_KEY;
    }
}
