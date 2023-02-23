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

public class Square extends Shape {
    private Double side;

    public Square(Double side) {
        this.side = side;
    }

    public Double computeArea() {
        return side * side;
    }

    public Double computePerimeter() {
        return 4 * side;
    }
}
