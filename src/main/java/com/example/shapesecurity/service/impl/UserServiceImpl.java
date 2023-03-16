package com.example.shapesecurity.service.impl;

import com.example.shapesecurity.model.Permission;
import com.example.shapesecurity.model.command.CreateUserCommand;
import com.example.shapesecurity.model.user.AuthenticationRequest;
import com.example.shapesecurity.model.user.AuthenticationResponse;
import com.example.shapesecurity.model.user.Role;
import com.example.shapesecurity.model.user.User;
import com.example.shapesecurity.repository.UserRepository;
import com.example.shapesecurity.service.JwtService;
import com.example.shapesecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Locale;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtServiceImpl;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthenticationResponse register(CreateUserCommand request) {
        var user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_USER)
                .build();
        repository.save(user);
        var jwtToken = jwtServiceImpl.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        var user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new EntityNotFoundException(
                String.format("User with email=%s has not been found", request.getEmail())));

        var jwtToken = jwtServiceImpl.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public void grantPermission(Permission permission) {
        User user = repository.findByEmail(permission.getEmail()).orElseThrow(() -> new EntityNotFoundException(
                String.format("User with email=%s has not been found", permission.getEmail())));
        if (Objects.equals(permission.getNameRole().toUpperCase(Locale.ROOT), "ADMIN")) {
            user.setRole(Role.ROLE_ADMIN);
            repository.save(user);
        }
        if (Objects.equals(permission.getNameRole().toUpperCase(Locale.ROOT), "CREATOR")) {
            user.setRole(Role.ROLE_CREATOR);
            repository.save(user);
        }
    }
}