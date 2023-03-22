package com.example.shapesecurity.mapper.specification;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.ShapeView;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("heightFrom")

public class HeightFrom implements ShapeSpecification{
    @Override
    public Specification<ShapeView> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("heightFrom") != null ?
                (root, query, cb) -> cb.and(
                        cb.isNotNull(root.get("height")),
                        cb.greaterThanOrEqualTo(root.get("height"), Double.parseDouble(filterRequest.getMap().get("heightFrom").toString()))
                ) : null;
    }


    @Override
    public String getSupportedField() {
        return "HeightFrom";
    }
}
