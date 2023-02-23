package com.example.shapesecurity.service;

import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.dto.ShapeDto;
import com.example.shapesecurity.model.shape.Shape;

public interface ShapeBuildService {
    Shape buildShape(CreateShapeCommand createShapeCommand);

    ShapeDto buildShapeDto(Shape shape);
}
