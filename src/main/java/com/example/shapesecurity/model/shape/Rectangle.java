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
public class Rectangle extends Shape {
    private Double width;
    private Double height;


    public Rectangle(Double width, Double height) {
        this.width = width;
        this.height = height;
    }

    public Double computeArea() {
        return width * height;
    }

    public Double computePerimeter() {
        return 2 * width + 2 * height;
    }
}