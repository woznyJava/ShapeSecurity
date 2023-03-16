package com.example.shapesecurity.mapper.specification;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.Shape;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("sideTo")
public class SideTo implements ShapeSpecification{
    @Override
    public Specification<Shape> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("sideTo") != null ? (root, query, cb) -> cb.lessThanOrEqualTo(root.get("side"), Double.parseDouble(filterRequest.getMap().get("sideTo").toString()) ) : null;
    }

    @Override
    public String getSupportedField() {
        return "SideTo";
    }
}
