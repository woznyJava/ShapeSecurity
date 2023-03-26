package com.example.shapesecurity.controller;

import com.example.shapesecurity.model.Permission;
import com.example.shapesecurity.model.command.CreateUserCommand;
import com.example.shapesecurity.model.user.AuthenticationRequest;
import com.example.shapesecurity.model.user.AuthenticationResponse;
import com.example.shapesecurity.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserServiceImpl service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid CreateUserCommand createUserCommand) {
        return ResponseEntity.ok(service.register(createUserCommand));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PutMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void grantPermission(@Valid @RequestBody Permission permission) {
        service.grantPermission(permission);
    }
}
