package com.example.shapesecurity.mapper.specification;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.Shape;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("version")
public class Version implements ShapeSpecification{
    @Override
    public Specification<Shape> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("version") != null ? (root, query, cb) -> cb.equal(root.get("version"), Long.valueOf(filterRequest.getMap().get("version").toString()) ) : null;

    }

    @Override
    public String getSupportedField() {
        return "Version";
    }
}
