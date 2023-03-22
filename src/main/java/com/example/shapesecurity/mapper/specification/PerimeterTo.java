package com.example.shapesecurity.mapper.specification;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.ShapeView;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("perimeterTo")
public class PerimeterTo implements ShapeSpecification{
    @Override
    public Specification<ShapeView> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("perimeterTo") != null ? (root, query, cb)
                -> cb.lessThanOrEqualTo(root.get("perimeter"), Double.parseDouble(filterRequest.getMap().get("perimeterTo").toString()) ) : null;
    }

    @Override
    public String getSupportedField() {
        return "PerimeterTo";
    }
}
