package com.example.shapesecurity.model.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateUserCommand {
    @NotBlank(message = "FIRST_NAME_NOT_EMPTY")
    private String firstName;
    @NotBlank(message = "LAST_NAME_NOT_EMPTY")
    private String lastName;
    @NotBlank(message = "EMAIL_NOT_EMPTY")
    private String email;
    @NotBlank(message = "PASSWORD_NOT_EMPTY")
    private String password;
}
