package com.example.shapesecurity.model.shape;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Circle extends Shape {

    private Double radius;

    public Circle(Double radius) {
        this.radius = radius;
    }

    public Double computeArea() {
        return 3.14 * radius * radius;
    }

    public Double computePerimeter() {
        return 2 * 3.14 * radius;
    }
}
