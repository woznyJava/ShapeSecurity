package com.example.shapesecurity.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NotBlank(message = "EMAIL_NOT_EMPTY")
    private String email;

    @NotBlank(message = "PASSWORD_NOT_EMPTY")
    private String password;
}