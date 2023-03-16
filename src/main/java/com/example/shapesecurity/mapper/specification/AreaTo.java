package com.example.shapesecurity.mapper.specification;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.Shape;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("areaTo")
public class AreaTo implements ShapeSpecification{
    @Override
    public Specification<Shape> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("areaTo") != null ? (root, query, cb) -> cb.lessThanOrEqualTo(root.get("area"),
                Double.parseDouble(filterRequest.getMap().get("areaTo").toString()) ) : null;    }

    @Override
    public String getSupportedField() {
        return "AreaTo";
    }
}
