package com.example.shapesecurity.strategy.filter;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.ShapeView;
import org.joda.time.LocalDateTime;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("createdAtFrom")
public class CreatedAtFrom implements ShapeSpecification {
    @Override
    public Specification<ShapeView> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("createdAtFrom") != null ? (root, query, cb)
                -> cb.lessThanOrEqualTo(root.get("createdAt"), LocalDateTime.parse((String) filterRequest.getMap().get("createdAtFrom"))) : null;
    }
}
