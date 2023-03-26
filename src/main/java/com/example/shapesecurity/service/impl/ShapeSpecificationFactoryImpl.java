package com.example.shapesecurity.service.impl;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.ShapeView;
import com.example.shapesecurity.service.ShapeSpecificationFactory;
import com.example.shapesecurity.strategy.filter.ShapeSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ShapeSpecificationFactoryImpl implements ShapeSpecificationFactory {
    private final Map<String, ShapeSpecification<?>> specifications;

    @Override
    public List<Specification<ShapeView>> createSpecifications(FilterRequest filterRequest) {
        List<Specification<ShapeView>> specs = new ArrayList<>();
        for (String key : filterRequest.getMap().keySet()) {
            if (specifications.containsKey(key)) {
                ShapeSpecification<?> spec = specifications.get(key);
                Specification<ShapeView> shapeSpec = (Specification<ShapeView>) spec.toSpecification(filterRequest);
                if (shapeSpec != null) {
                    specs.add(shapeSpec);
                }
            }
        }
        return specs;
    }
}
