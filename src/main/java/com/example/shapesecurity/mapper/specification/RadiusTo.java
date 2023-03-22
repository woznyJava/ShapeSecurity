package com.example.shapesecurity.mapper.specification;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.ShapeView;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("radiusTo")
public class RadiusTo implements ShapeSpecification{
    @Override
    public Specification<ShapeView> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("radiusTo") != null ? (root, query, cb)
                -> cb.lessThanOrEqualTo(root.get("radius"), Double.parseDouble(filterRequest.getMap().get("radiusTo").toString()) ) : null;
    }

    @Override
    public String getSupportedField() {
        return "RadiusTo";
    }
}
