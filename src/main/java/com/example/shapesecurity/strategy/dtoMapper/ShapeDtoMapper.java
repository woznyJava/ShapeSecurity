package com.example.shapesecurity.strategy.dtoMapper;

import com.example.shapesecurity.model.dto.ShapeDto;
import com.example.shapesecurity.model.shape.Shape;

@FunctionalInterface
public interface ShapeDtoMapper {

    ShapeDto mapShapeToShapeDto(Shape shape);
}
