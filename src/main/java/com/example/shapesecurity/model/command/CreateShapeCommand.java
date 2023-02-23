package com.example.shapesecurity.model.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateShapeCommand {
    @NotBlank(message = "TYPE_NOT_EMPTY")
    private String type;
    @NotBlank(message = "PARAMETERS_NOT_EMPTY")
    private Map<String, Double> parameters;
}
