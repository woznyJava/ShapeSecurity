package com.example.shapesecurity.strategy.filter;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.ShapeView;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("createdBy")
public class CreatedBy implements ShapeSpecification {
    @Override
    public Specification<ShapeView> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("createdBy") != null ? (root, query, cb) -> cb.equal(root.get("createdBy"), filterRequest.getMap().get("createdBy").toString()) : null;
    }
}
