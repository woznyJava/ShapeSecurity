package com.example.shapesecurity.strategy.filter;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.ShapeView;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("shapeType")
public class ShapeType implements ShapeSpecification {

    @Override
    public Specification<ShapeView> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("shapeType") != null ?
                (root, query, cb) -> cb.equal(root.get("type"), filterRequest.getMap().get("shapeType").toString()) : null;
    }
}
