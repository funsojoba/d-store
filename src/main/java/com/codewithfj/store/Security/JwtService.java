package com.codewithfj.store.Security;


import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    public String generateToken(String email) {
        // later move to env
        String SECRET_KEY = "k8YxvP3Qw7NfZbL2tR9uA6JdH1sEoC5mXqV4GgU0yKpIhWz8FjB2cD7nSxM3eT9aR5YwQpL6uZf1KcJ8H0GdVtX4sEoN2mB7rA9iC3WqY5PzT6UjFhD8lS0vXnR1bM4eGkH2cJ7pQ9LwT3ZfA6sEoU8yX0VdN1mR5iB4GkH2cJ7pQ9LwT3ZfA6sEoU8yX0VdN1mR5iB4GkH2cJ7pQ9LwT3ZfA6sEoU8yX0VdN1mR5iB4"; // Move this to .env later
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
