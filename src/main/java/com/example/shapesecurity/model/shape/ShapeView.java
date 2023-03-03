package com.example.shapesecurity.model.shape;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SHAPE_VIEW")
public class ShapeView {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double area;
    private double perimeter;
    @OneToOne
    @JoinColumn(name = "SHAPE_ID")
    @JsonIgnoreProperties("shapeView")
    private Shape shape;

    public ShapeView(double area, double perimeter, Shape shape) {
        this.area = area;
        this.perimeter = perimeter;
        this.shape = shape;
    }
}
