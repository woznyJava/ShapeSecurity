package com.example.shapesecurity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class Permission {
    @NotBlank(message = "EMAIL_NOT_EMPTY")
    private String email;
    @NotBlank(message = "NAME_ROLE_NOT_EMPTY")
    private String nameRole;
}
