package com.example.shapesecurity.service.impl;

import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.dto.ShapeDto;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.service.ShapeBuildService;
import com.example.shapesecurity.strategy.builder.ShapeBuilder;
import com.example.shapesecurity.strategy.dtoMapper.ShapeDtoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShapeBuildServiceImpl implements ShapeBuildService {
    private static final String DTO = "DTO";
    private final Map<String, ShapeBuilder> shapeBuilders;
    private final Map<String, ShapeDtoMapper> shapeMapperMap;

    @Override
    public Shape buildShape(CreateShapeCommand createShapeCommand) {
        return shapeBuilders.get(createShapeCommand.getType().toUpperCase())
                .getNewShape(createShapeCommand);
    }

    @Override
    public ShapeDto buildShapeDto(Shape shape) {
        return shapeMapperMap.get(shape.getType().toUpperCase() + DTO).mapShapeToShapeDto(shape);
    }
}
