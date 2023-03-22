package com.example.shapesecurity.service.impl;

import com.example.shapesecurity.mapper.CriteriaFilterShape;
import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.dto.ShapeDto;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.model.shape.ShapeView;
import com.example.shapesecurity.repository.ShapeRepository;
import com.example.shapesecurity.repository.ShapeViewRepository;
import com.example.shapesecurity.service.ShapeBuildService;
import com.example.shapesecurity.service.ShapeService;
import com.example.shapesecurity.service.ShapeViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ShapeServiceImpl implements ShapeService {
    private final ShapeRepository shapeRepository;
    private final ShapeBuildService shapeBuildService;
    private final ShapeViewService shapeViewService;
    private final CriteriaFilterShape criteriaFilterShape;
    private final ShapeSpecificationFactory shapeSpecificationFactory;
    private final ShapeViewRepository shapeViewRepository;

    @Override
    public ShapeDto save(CreateShapeCommand createShapeCommand) {
        Map<String, Object> map = shapeBuildService.buildShape(createShapeCommand);
        Shape shape = (Shape) map.get("Shape");
        shapeRepository.save(shape);
//        shapeViewService.save((ShapeView) map.get("ShapeView"));
        return shapeBuildService.buildShapeDto(shape);
    }

    @Override
    public List<ShapeDto> filter(FilterRequest filterRequest) {
        return criteriaFilterShape.testNowy(filterRequest);
    }

    @Override
    public List<ShapeView> filtruj(FilterRequest filterRequest) {
        List<Specification<ShapeView>> specs = shapeSpecificationFactory.createSpecifications(filterRequest);
        Specification<ShapeView> combinedSpecs = Specification.where(specs.get(0));

        for (int i = 1; i < specs.size(); i++) {
            combinedSpecs = combinedSpecs.and(specs.get(i));
        }
        return shapeViewRepository.findAll(combinedSpecs);
    }
}
