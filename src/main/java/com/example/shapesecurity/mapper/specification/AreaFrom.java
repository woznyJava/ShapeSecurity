package com.example.shapesecurity.mapper.specification;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.Shape;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("areaFrom")
public class AreaFrom implements ShapeSpecification {
    @Override
    public Specification<Shape> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("areaFrom") != null ? (root, query, cb) -> cb.lessThanOrEqualTo(root.get("area"),
                Double.parseDouble(filterRequest.getMap().get("areaFrom").toString()) ) : null;    }

    @Override
    public String getSupportedField() {
        return "AreaFrom";
    }
}