package com.example.shapesecurity.service;

import com.example.shapesecurity.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {

    String extractUserName(String token);

    String generateToken(User user);

    String generateToken(Map<String, Object> extraClaims,
                         UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
