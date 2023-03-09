package com.example.shapesecurity.strategy.updateMapper;

import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.model.shape.ShapeView;

import java.util.Map;

@FunctionalInterface
public interface ShapeUpdate {
    Map<String,Object> updateShape(UpdateShapeCommand updateShapeCommand, Shape shape, ShapeView shapeView);
}
