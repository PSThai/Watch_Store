package com.example.UserService.utils;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;


@Component
public class Utils {
	private static final long JWT_EXPIRATION_MS = 3600000; // Token expiration time: 1 hour

    public static String createJwtToken(String userName) {
        // Generate a secure secret key
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);

        // Create JWT token with user authentication information
        return Jwts.builder()
                .setSubject(userName) // Set the subject of the token to the user's username
                .setIssuedAt(new Date()) // Set the token's issue time to the current time
                .setExpiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MS)) // Set the token's expiration time
                .signWith(key) // Sign the token with the secure secret key
                .compact(); // Generate the JWT token string
    }

}
