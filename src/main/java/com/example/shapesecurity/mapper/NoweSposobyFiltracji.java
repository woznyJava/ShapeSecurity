package com.example.shapesecurity.mapper;

import com.example.shapesecurity.model.shape.*;
import com.example.shapesecurity.repository.ShapeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.function.Function;

@Repository
@RequiredArgsConstructor
public class NoweSposobyFiltracji {
    private final ShapeRepository shapeRepository;


    private final Map<String, Function<Shape, Object>> shapeFieldMappings = Map.of(
            "sideFrom", s -> s instanceof Square ? ((Square) s).getSide() : null,
            "sideTo", s -> s instanceof Square ? ((Square) s).getSide() : null,
            "radiusFrom", s -> s instanceof Circle ? ((Circle) s).getRadius() : null,
            "radiusTo", s -> s instanceof Circle ? ((Circle) s).getRadius() : null,
            "heightFrom", s -> s instanceof Rectangle ? ((Rectangle) s).getHeight() : null,
            "heightTo", s -> s instanceof Rectangle ? ((Rectangle) s).getHeight() : null,
            "widthFrom", s -> s instanceof Rectangle ? ((Rectangle) s).getWidth() : null,
            "widthTo", s -> s instanceof Rectangle ? ((Rectangle) s).getWidth() : null
    );

    private final Map<String, Function<ShapeView, Object>> shapeViewFieldMappings = Map.of(
            "areaFrom", ShapeView::getArea,
            "areaTo", ShapeView::getArea,
            "perimeterFrom", ShapeView::getPerimeter,
            "perimeterTo", ShapeView::getPerimeter
    );
//  Optional<Double> areaFrom = Optional.ofNullable(filter.getAreaFrom());
//        Optional<Double> areaTo = Optional.ofNullable(filter.getAreaTo());
//
//        if (areaFrom.isPresent() && areaTo.isPresent()) {
//            predicates.add(criteriaBuilder.between(root.get("shapeView").get("area"), areaFrom.get(), areaTo.get()));
//        } else if (areaFrom.isPresent()) {
//            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("shapeView").get("area"), areaFrom.get()));
//        } else if (areaTo.isPresent()) {
//            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("shapeView").get("area"), areaTo.get()));
//        }
//    public List<Shape> findShapesByFilter(FilterRequest filter) {
//        Specification<Shape> spec = shapeViewSpecification(filter);
//        return shapeRepository.findAll(spec);
//    }

//    public Specification<Shape> shapeViewSpecification(FilterRequest filter) {
//        return (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            shapeViewFieldMappings.forEach((fieldName, fieldMapping) ->
//                    Optional.ofNullable(filter.getFieldValue(fieldName))
//                            .map(value -> criteriaBuilder.greaterThanOrEqualTo(fieldMapping.apply(root.get("shapeView")), value))
//                            .ifPresent(predicates::add));
//
//            shapeFieldMappings.forEach((fieldName, fieldMapping) ->
//                    Optional.ofNullable(filter.getFieldValue(fieldName))
//                            .map(value -> criteriaBuilder.equal(fieldMapping.apply(root), value))
//                            .ifPresent(predicates::add));
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
////        };
//    }
//    }



//    public Specification<Shape> shapeViewSpecification(FilterRequest filter) {
//        return (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            // ShapeView mappings
//            Map<String, Function<ShapeView, Object>> shapeViewFieldMappings = new HashMap<>();
//            shapeViewFieldMappings.put("areaFrom", s -> s.getArea());
//            shapeViewFieldMappings.put("areaTo", s -> s.getArea());
//            shapeViewFieldMappings.put("perimeterFrom", s -> s.getPerimeter());
//            shapeViewFieldMappings.put("perimeterTo", s -> s.getPerimeter());
//
//            for (String fieldName : shapeViewFieldMappings.keySet()) {
//                Object value = filter.get(fieldName);
//                if (value != null) {
//                    Function<ShapeView, Object> fieldMapping = shapeViewFieldMappings.get(fieldName);
//                    Object fieldValue = fieldMapping.apply(root.get("shapeView"));
//                    if (fieldValue != null) {
//                        predicates.add(criteriaBuilder.equal(criteriaBuilder.prod(fieldValue, 1), value));
//                    }
//                }
//            }
//
//            // Shape mappings
//            Map<String, Function<Shape, Object>> shapeFieldMappings = new HashMap<>();
//            shapeFieldMappings.put("sideFrom", s -> s instanceof Square ? ((Square) s).getSide() : null);
//            shapeFieldMappings.put("sideTo", s -> s instanceof Square ? ((Square) s).getSide() : null);
//            shapeFieldMappings.put("radiusFrom", s -> s instanceof Circle ? ((Circle) s).getRadius() : null);
//            shapeFieldMappings.put("radiusTo", s -> s instanceof Circle ? ((Circle) s).getRadius() : null);
//            shapeFieldMappings.put("heightFrom", s -> s instanceof Rectangle ? ((Rectangle) s).getHeight() : null);
//            shapeFieldMappings.put("heightTo", s -> s instanceof Rectangle ? ((Rectangle) s).getHeight() : null);
//            shapeFieldMappings.put("widthFrom", s -> s instanceof Rectangle ? ((Rectangle) s).getWidth() : null);
//            shapeFieldMappings.put("widthTo", s -> s instanceof Rectangle ? ((Rectangle) s).getWidth() : null);
//
//            for (String fieldName : shapeFieldMappings.keySet()) {
//                Object value = filter.get(fieldName);
//                if (value != null) {
//                    Function<Shape, Object> shapeFieldMapping = shapeFieldMappings.get(fieldName);
//                    Object fieldValue = shapeFieldMapping.apply(root);
//                    if (fieldValue != null) {
//                        predicates.add(criteriaBuilder.equal(criteriaBuilder.prod(fieldValue, 1), value));
//                    }
//                }
//            }
//
//            if (filter.getCreatedBy() != null) {
//                predicates.add((Predicate) criteriaBuilder.equal(root.get("createdBy"), filter.getCreatedBy()));
//            }
//
//            if (filter.getShapeType() != null) {
//                predicates.add((Predicate) criteriaBuilder.equal(root.get("type"), filter.getShapeType()));
//            }
//
//            if (filter.getVersion() != null) {
//                predicates.add((Predicate) criteriaBuilder.equal(root.get("version"), filter.getVersion()));
//            }
//
//            if (filter.getCreatedAtFrom() != null) {
//                predicates.add((Predicate) criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), filter.getCreatedAtFrom()));
//            }
//
//            if (filter.getCreatedAtTo() != null) {
//                predicates.add((Predicate) criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), filter.getCreatedAtTo()));
//            }
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        };
//    }
//}

//    private final EntityManager entityManager;
//        public Specification<Shape> shapeViewSpecification(FilterRequest filter) {
//            return (root, query, criteriaBuilder) -> {
//                List<Predicate> predicates = new ArrayList<>();
//
//                // Mapowanie pól z ShapeViewFilter na odpowiednie metody pobierające wartość z ShapeView
//                Map<String, Function<ShapeView, Object>> fieldMappings = new HashMap<>();
//                fieldMappings.put("areaFrom", ShapeView::getAreaFrom);
//                fieldMappings.put("areaTo", ShapeView::getAreaTo);
//                fieldMappings.put("perimeterFrom", ShapeView::getPerimeterFrom);
//                fieldMappings.put("perimeterTo", ShapeView::getPerimeterTo);
//
//                // Dla każdego pola z ShapeViewFilter, które nie jest nullem, pobieramy odpowiadającą mu wartość z ShapeView
//                // i dodajemy predykat do listy predicates
//                for (String fieldName : fieldMappings.keySet()) {
//                    Object value = filter.get(fieldName);
//                    if (value != null) {
//                        Function<ShapeView, Object> fieldMapping = fieldMappings.get(fieldName);
//                        Object fieldValue = fieldMapping.apply(root.get("shapeView"));
//                        if (fieldValue != null) {
//                            predicates.add(criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.prod(fieldValue, 1), (Number) value));
//                        }
//                    }
//                }
//
//                // Mapowanie pól z ShapeView na odpowiednie metody pobierające wartość z Shape
//                Map<String, Function<Shape, Object>> shapeFieldMappings = new HashMap<>();
//                shapeFieldMappings.put("sideFrom", s -> s instanceof Square ? ((Square) s).getSide() : null);
//                shapeFieldMappings.put("sideTo", s -> s instanceof Square ? ((Square) s).getSide() : null);
//                shapeFieldMappings.put("radiusFrom", s -> s instanceof Circle ? ((Circle) s).getRadius() : null);
//                shapeFieldMappings.put("radiusTo", s -> s instanceof Circle ? ((Circle) s).getRadius() : null);
//                shapeFieldMappings.put("heightFrom", s -> s instanceof Rectangle ? ((Rectangle) s).getHeight() : null);
//                shapeFieldMappings.put("heightTo", s -> s instanceof Rectangle ? ((Rectangle) s).getHeight() : null);
//                shapeFieldMappings.put("widthFrom", s -> s instanceof Rectangle ? ((Rectangle) s).getWidth() : null);
//                shapeFieldMappings.put("widthTo", s -> s instanceof Rectangle ? ((Rectangle) s).getWidth() : null);
//
//                // Dla każdego pola z ShapeView, które nie jest nullem, pobieramy odpowiadającą mu wartość z Shape
//                // i dodajemy predykat do listy predicates
//                for (String fieldName : shapeFieldMappings.keySet()) {
//                    Object value = filter.get(fieldName);
//                    if (value != null) {
//                        Function<Shape, Object> shapeFieldMapping = shapeFieldMappings.get(fieldName);
//                        Object fieldValue = shapeFieldMapping.apply(root.get("shapeView").get("shape"));
//                        if (fieldValue != null) {
//                            predicates.add(criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.prod(fieldValue, 1), (Number) value));
//                        }
//                    }
//                }
//
//                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//            };
//        }
//
//    }
}
