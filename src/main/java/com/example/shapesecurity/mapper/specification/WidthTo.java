package com.example.shapesecurity.mapper.specification;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.Shape;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("widthTo")
public class WidthTo implements ShapeSpecification{
    @Override
    public Specification<Shape> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("widthTo") != null ? (root, query, cb) -> cb.lessThanOrEqualTo(root.get("width"), Double.parseDouble(filterRequest.getMap().get("widthTo").toString())  ) : null;
    }

    @Override
    public String getSupportedField() {
        return "WidthTo";
    }
}
