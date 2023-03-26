package com.example.shapesecurity.strategy.filter;

import com.example.shapesecurity.model.FilterRequest;
import org.springframework.data.jpa.domain.Specification;

public interface ShapeSpecification<T> {
    Specification<T> toSpecification(FilterRequest filterRequest);

//    String getSupportedField();

}

