package com.example.shapesecurity.strategy.filter;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.ShapeView;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component("areaTo")
public class AreaTo implements ShapeSpecification {
    @Override
    public Specification<ShapeView> toSpecification(FilterRequest filterRequest) {
        return filterRequest.getMap().get("areaTo") != null ? (root, query, cb) -> cb.lessThanOrEqualTo(root.get("area"),
                Double.parseDouble(filterRequest.getMap().get("areaTo").toString())) : null;
    }

}
