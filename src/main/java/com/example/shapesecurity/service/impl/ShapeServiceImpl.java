package com.example.shapesecurity.service.impl;

import com.example.shapesecurity.mapper.CriteriaFilter;
import com.example.shapesecurity.mapper.CriteriaNaCzysto;
import com.example.shapesecurity.mapper.CriteriaShape;
import com.example.shapesecurity.mapper.Qquery;
import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.dto.ShapeDto;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.model.shape.ShapeView;
import com.example.shapesecurity.repository.ShapeRepository;
import com.example.shapesecurity.service.ShapeBuildService;
import com.example.shapesecurity.service.ShapeService;
import com.example.shapesecurity.service.UpdateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShapeServiceImpl implements ShapeService {
    private final ShapeRepository shapeRepository;
    private final ShapeBuildService shapeBuildService;
    private final UpdateService updateService;
    private final CriteriaShape criteriaShape;
    private final CriteriaNaCzysto criteriaNaCzysto;
    private final ShapeViewServiceImpl shapeViewService;
    private final CriteriaFilter criteriaFilter;
    private final Qquery qquery;

    @Override

    public ShapeDto save(@Valid CreateShapeCommand createShapeCommand) {
        Map<String, Object> map = shapeBuildService.buildShape(createShapeCommand);
        shapeRepository.save((Shape) map.get("Shape"));
        shapeViewService.save((ShapeView) map.get("ShapeView"));
        return shapeBuildService.buildShapeDto((Shape) map.get("Shape"));
    }

    @Override
    public List<ShapeDto> filter(FilterRequest filterRequest) {
        return qquery.testNowy(filterRequest);
//        return criteriaNaCzysto.filterShapes3(filterRequest);
//        return shapeRepository.findShapes(filterRequest.getCreatedBy(), filterRequest.getShapeType(), filterRequest.getAreaFrom()
//                , filterRequest.getAreaTo(), filterRequest.getPerimeterFrom(), filterRequest.getPerimeterTo(), filterRequest.getCreatedAtFrom(),
//                filterRequest.getCreatedAtTo(), filterRequest.getVersion(), filterRequest.getSideFrom(), filterRequest.getSideTo(),
//                filterRequest.getWidthFrom(), filterRequest.getWidthTo(), filterRequest.getHeightFrom(), filterRequest.getHeightTo()
//                , filterRequest.getRadiusFrom(), filterRequest.getRadiusTo());
    }

    @Transactional
    @Override
    public ShapeDto update(UpdateShapeCommand updateShapeCommand, int id) {
        Shape shape = updateService.update(updateShapeCommand, id );
        return shapeBuildService.buildShapeDto(shape);
    }
}
