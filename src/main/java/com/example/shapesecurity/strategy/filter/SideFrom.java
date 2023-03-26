package com.example.shapesecurity.strategy.filter;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.ShapeView;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("sideFrom")
public class SideFrom implements ShapeSpecification {
    @Override
    public Specification<ShapeView> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("sideFrom") != null ? (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("side"), Double.parseDouble(filterRequest.getMap().get("sideFrom").toString())) : null;
    }
}
