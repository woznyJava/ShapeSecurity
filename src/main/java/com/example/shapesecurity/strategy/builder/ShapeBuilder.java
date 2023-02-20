package com.example.shapesecurity.strategy.builder;

import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.shape.Shape;
@FunctionalInterface
public interface ShapeBuilder {
    Shape getNewShape(CreateShapeCommand createShapeCommand);
}
