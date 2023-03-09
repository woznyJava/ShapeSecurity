package com.example.shapesecurity.model.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateUserCommand {
//    @NotNull(message = "FIRST_NAME_NOT_NULL")
    @NotBlank(message = "FIRST_NAME_NOT_EMPTY")
    private String firstName;
//    @NotNull(message = "FIRST_NAME_NOT_NULL")
    @NotBlank(message = "LAST_NAME_NOT_EMPTY")
    private String lastName;
//    @NotNull(message = "FIRST_NAME_NOT_NULL")
    @NotBlank(message = "EMAIL_NOT_EMPTY")
    private String email;
//    @NotNull(message = "FIRST_NAME_NOT_NULL")
    @NotBlank(message = "PASSWORD_NOT_EMPTY")
    private String password;

}
