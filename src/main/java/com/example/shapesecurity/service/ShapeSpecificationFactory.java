package com.example.shapesecurity.service;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.ShapeView;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ShapeSpecificationFactory {
    List<Specification<ShapeView>> createSpecifications(FilterRequest filterRequest);
}
