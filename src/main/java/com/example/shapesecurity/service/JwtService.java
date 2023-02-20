package com.example.shapesecurity.service;

import com.example.shapesecurity.model.user.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {

    String extractUserName(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    String generateToken(User user);
    String generateToken( Map<String, Object> extraClaims,
                          UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
