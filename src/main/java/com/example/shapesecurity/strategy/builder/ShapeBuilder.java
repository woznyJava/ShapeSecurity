package com.example.shapesecurity.strategy.builder;

import com.example.shapesecurity.model.command.CreateShapeCommand;

import java.util.Map;

@FunctionalInterface
public interface ShapeBuilder {
    Map<String, Object> getNewShape(CreateShapeCommand createShapeCommand);
}
