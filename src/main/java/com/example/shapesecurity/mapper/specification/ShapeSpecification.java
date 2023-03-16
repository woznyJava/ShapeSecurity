package com.example.shapesecurity.mapper.specification;

import com.example.shapesecurity.model.FilterRequest;
import org.springframework.data.jpa.domain.Specification;

public interface ShapeSpecification<T> {
    Specification<T> toSpecification(FilterRequest filterRequest);

    String getSupportedField();

}

