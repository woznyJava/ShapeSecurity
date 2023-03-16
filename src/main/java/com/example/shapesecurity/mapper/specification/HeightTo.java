package com.example.shapesecurity.mapper.specification;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.Shape;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("heightTo")
public class HeightTo implements ShapeSpecification{
    @Override
    public Specification<Shape> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("heightTo") != null ? (root, query, cb) -> cb.lessThanOrEqualTo(root.get("height"),
                Double.parseDouble(filterRequest.getMap().get("heightTo").toString()) ) : null;
    }

    @Override
    public String getSupportedField() {
        return "HeighTo";
    }
}
