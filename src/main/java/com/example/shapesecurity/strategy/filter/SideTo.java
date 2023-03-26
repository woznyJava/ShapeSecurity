package com.example.shapesecurity.strategy.filter;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.ShapeView;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("sideTo")
public class SideTo implements ShapeSpecification {
    @Override
    public Specification<ShapeView> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("sideTo") != null ? (root, query, cb) -> cb.lessThanOrEqualTo(root.get("side"), Double.parseDouble(filterRequest.getMap().get("sideTo").toString())) : null;
    }
}
