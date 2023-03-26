package com.example.shapesecurity.service.impl;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.dto.ShapeDto;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.model.shape.ShapeView;
import com.example.shapesecurity.repository.ShapeRepository;
import com.example.shapesecurity.repository.ShapeViewRepository;
import com.example.shapesecurity.service.ShapeBuildService;
import com.example.shapesecurity.service.ShapeService;
import com.example.shapesecurity.service.ShapeSpecificationFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShapeServiceImpl implements ShapeService {
    private final ShapeRepository shapeRepository;
    private final ShapeBuildService shapeBuildService;
    private final ShapeSpecificationFactory shapeSpecificationFactory;
    private final ShapeViewRepository shapeViewRepository;

    @Override
    public ShapeDto save(CreateShapeCommand createShapeCommand) {
        Shape shape = shapeBuildService.buildShape(createShapeCommand);
        shapeRepository.save(shape);
        return shapeBuildService.buildShapeDto(shape);
    }

    @Override
    public List<ShapeDto> filter(FilterRequest filterRequest) {
        List<Specification<ShapeView>> specs = shapeSpecificationFactory.createSpecifications(filterRequest);
        if (specs.isEmpty()) {
            List<ShapeView> allShapes = shapeViewRepository.findAll();
            return shapeBuildService.buildShapeDtoListFromListShapeView(allShapes);
        } else {
            Specification<ShapeView> combinedSpecs = Specification.where(specs.get(0));

            for (int i = 1; i < specs.size(); i++) {
                combinedSpecs = combinedSpecs.and(specs.get(i));
            }

            List<ShapeView> shapeViews = shapeViewRepository.findAll(combinedSpecs);
            return shapeBuildService.buildShapeDtoListFromListShapeView(shapeViews);
        }
    }
}
