package com.example.shapesecurity.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShapeFilterServiceImpl {






//    public List<Specification<Shape>> getSpecificationsFromFilterRequest(FilterRequest filterRequest, ShapeSpecificationFactory specificationFactory) {
//        List<Specification<Shape>> specifications = new ArrayList<>();
//        for (String key : filterRequest.getMap().keySet()) {
//            ShapeSpecification<?> specification = specificationFactory.getSpecification(key);
//            if (specification != null) {
//                Specification<Shape> shapeSpecification = specification.toSpecification(filterRequest);
//                if (shapeSpecification != null) {
//                    specifications.add(shapeSpecification);
//                }
//            }
//        }
//        return specifications;
//    }
}
