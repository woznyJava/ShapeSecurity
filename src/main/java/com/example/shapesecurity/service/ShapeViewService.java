package com.example.shapesecurity.service;

import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.model.shape.ShapeView;

public interface ShapeViewService {
    void save(ShapeView shapeView);
    ShapeView create(Shape shape);
}
