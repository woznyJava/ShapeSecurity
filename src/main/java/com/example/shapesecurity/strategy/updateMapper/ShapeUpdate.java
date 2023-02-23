package com.example.shapesecurity.strategy.updateMapper;

import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.shape.Shape;

@FunctionalInterface
public interface ShapeUpdate {
    Shape updateShape(UpdateShapeCommand updateShapeCommand, Shape shape);
}
