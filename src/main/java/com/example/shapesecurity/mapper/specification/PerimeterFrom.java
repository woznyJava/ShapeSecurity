package com.example.shapesecurity.mapper.specification;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.ShapeView;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("perimeterFrom")
public class PerimeterFrom implements ShapeSpecification{
    @Override
    public Specification<ShapeView> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("perimeterFrom") != null ? (root, query, cb)
                -> cb.greaterThanOrEqualTo(root.get("perimeter"), Double.parseDouble(filterRequest.getMap().get("perimeterFrom").toString())) : null;
    }

    @Override
    public String getSupportedField() {
        return "PerimeterFrom";
    }
}
