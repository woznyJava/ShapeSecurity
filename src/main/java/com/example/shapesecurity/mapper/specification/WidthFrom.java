package com.example.shapesecurity.mapper.specification;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.Shape;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("widthFrom")
public class WidthFrom implements ShapeSpecification{
    @Override
    public Specification<Shape> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("widthFrom") != null ? (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("width"), Double.parseDouble(filterRequest.getMap().get("widthFrom").toString()) ) : null;
    }

    @Override
    public String getSupportedField() {
        return "WidthFrom";
    }
}
