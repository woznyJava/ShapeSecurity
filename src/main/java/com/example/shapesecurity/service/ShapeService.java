package com.example.shapesecurity.service;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.dto.ShapeDto;
import com.example.shapesecurity.model.shape.ShapeView;

import java.util.List;

public interface ShapeService {
    ShapeDto save(CreateShapeCommand createShapeCommand);

    List<ShapeDto> filter(FilterRequest filterRequest);
    List<ShapeView> filtruj(FilterRequest filterRequest);
}
