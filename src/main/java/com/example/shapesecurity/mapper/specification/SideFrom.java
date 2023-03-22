package com.example.shapesecurity.mapper.specification;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.Shape;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("sideFrom")
public class SideFrom implements ShapeSpecification{
    @Override
    public Specification<Shape> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("sideFrom") != null ? (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("side"), Double.parseDouble(filterRequest.getMap().get("sideFrom").toString()) ) : null;
    }

    @Override
    public String getSupportedField() {
        return "SideFrom";
    }
}
