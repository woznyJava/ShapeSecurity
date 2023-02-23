package com.example.shapesecurity.service.impl;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.dto.ShapeDto;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.repository.ShapeRepository;
import com.example.shapesecurity.service.ShapeBuildService;
import com.example.shapesecurity.service.ShapeService;
import com.example.shapesecurity.service.UpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShapeServiceImpl implements ShapeService {
    private final ShapeRepository shapeRepository;
    private final ShapeBuildService shapeBuildService;
    private final UpdateService updateService;

    @Override
    public ShapeDto save(@Valid CreateShapeCommand createShapeCommand) {
        Shape shape = shapeBuildService.buildShape(createShapeCommand);
        shapeRepository.save(shape);
        return shapeBuildService.buildShapeDto(shape);
    }

    @Override
    public List<Shape> filter(FilterRequest filterRequest) {
        return shapeRepository.findShapes(filterRequest.getCreatedBy(), filterRequest.getShapeType(), filterRequest.getAreaFrom()
                , filterRequest.getAreaTo(), filterRequest.getPerimeterFrom(), filterRequest.getPerimeterTo(), filterRequest.getCreatedAtFrom(),
                filterRequest.getCreatedAtTo(), filterRequest.getVersion(), filterRequest.getSideFrom(), filterRequest.getSideTo(),
                filterRequest.getWidthFrom(), filterRequest.getWidthTo(), filterRequest.getHeightFrom(), filterRequest.getHeightTo()
                , filterRequest.getRadiusFrom(), filterRequest.getRadiusTo());
    }

    @Transactional
    @Override
    public ShapeDto update(UpdateShapeCommand updateShapeCommand) {
        Shape shape = updateService.update(updateShapeCommand);
        return shapeBuildService.buildShapeDto(shape);
    }

    @PostConstruct
    private void createView() {
        shapeRepository.createView();
    }
}
