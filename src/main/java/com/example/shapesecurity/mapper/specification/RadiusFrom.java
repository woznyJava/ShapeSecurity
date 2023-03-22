package com.example.shapesecurity.mapper.specification;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.ShapeView;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("radiusFrom")
public class RadiusFrom implements ShapeSpecification{
    @Override
    public Specification<ShapeView> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("radiusFrom") != null ? (root, query, cb)
                -> cb.greaterThanOrEqualTo(root.get("radius"), Double.parseDouble(filterRequest.getMap().get("radiusFrom").toString()) ) : null;
    }
    @Override
    public String getSupportedField() {
        return "RadiusFrom";
    }
}
