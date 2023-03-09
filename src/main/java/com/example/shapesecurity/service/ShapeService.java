package com.example.shapesecurity.service;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.dto.ShapeDto;

import java.util.List;

public interface ShapeService {
    ShapeDto save(CreateShapeCommand createShapeCommand);

    List<ShapeDto> filter(FilterRequest filterRequest);

    ShapeDto update(UpdateShapeCommand updateShapeCommand, int id) throws InterruptedException;
}
