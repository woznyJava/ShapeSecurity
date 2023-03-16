package com.example.shapesecurity.service.impl;

import com.example.shapesecurity.mapper.specification.ShapeSpecification;
import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.Shape;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ShapeSpecificationFactory {

    private final Map<String, ShapeSpecification<?>> specifications;


    public List<Specification<Shape>> createSpecifications(FilterRequest filterRequest) {
        List<Specification<Shape>> specs = new ArrayList<>();
        for (String key : filterRequest.getMap().keySet()) {
            if (specifications.containsKey(key)) {
                ShapeSpecification<?> spec = specifications.get(key);
                Specification<Shape> shapeSpec = (Specification<Shape>) spec.toSpecification(filterRequest);
                if (shapeSpec != null) {
                    specs.add(shapeSpec);
                }
            }
        }
        return specs;
    }
}
