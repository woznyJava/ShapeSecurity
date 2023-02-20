package com.example.shapesecurity.service;

import com.example.shapesecurity.model.Permission;
import com.example.shapesecurity.model.command.CreateUserCommand;
import com.example.shapesecurity.model.user.AuthenticationRequest;
import com.example.shapesecurity.model.user.AuthenticationResponse;

public interface UserService {
    AuthenticationResponse register(CreateUserCommand request);

    AuthenticationResponse authenticate(AuthenticationRequest request);

    void grantPermission(Permission permission);

}
