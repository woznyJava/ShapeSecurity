package com.example.shapesecurity.service;

import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.dto.ShapeDto;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.model.shape.ShapeView;

import java.util.List;

public interface ShapeBuildService {
    Shape buildShape(CreateShapeCommand createShapeCommand);

    ShapeDto buildShapeDto(Shape shape);

    List<ShapeDto> buildShapeDtoListFromListShapeView(List<ShapeView> list);
}
