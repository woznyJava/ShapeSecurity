package com.example.shapesecurity.mapper.specification;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.Shape;
import org.joda.time.LocalDateTime;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("createdAtTo")
public class CreatedAtTo implements ShapeSpecification{
    @Override
    public Specification<Shape> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("createdAtTo") != null ? (root, query, cb) -> cb.lessThanOrEqualTo(root.get("createdAt"), LocalDateTime.parse((String) filterRequest.getMap().get("createdAtTo"))) : null;
    }

    @Override
    public String getSupportedField() {
        return "CreatedAtTo";
    }
}
