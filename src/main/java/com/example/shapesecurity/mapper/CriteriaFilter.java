package com.example.shapesecurity.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class CriteriaFilter {
    private final EntityManager entityManager;


//    public Specification<Shape> shapeViewSpecification(FilterRequest filter) {
//        return (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//            Map<String, Function<ShapeView, Object>> fieldMappings = new HashMap<>();
//            fieldMappings.put("areaFrom", s -> s.getAreaFrom());
//            fieldMappings.put("areaTo", s -> s.getAreaTo());
//            fieldMappings.put("perimeterFrom", s -> s.getPerimeterFrom());
//            fieldMappings.put("perimeterTo", s -> s.getPerimeterTo());
//
//            for (String fieldName : fieldMappings.keySet()) {
//                Object value = filter.get(fieldName);
//                if (value != null) {
//                    Function<ShapeView, Object> fieldMapping = fieldMappings.get(fieldName);
//                    Object fieldValue = fieldMapping.apply(root.get("shapeView"));
//                    if (fieldValue != null) {
//                        predicates.add(criteriaBuilder.equal(criteriaBuilder.prod(fieldValue, 1), value));
//                    }
//                }
//            }
//
//            Map<String, Function<Shape, Object>> shapeFieldMappings = new HashMap<>();
//            shapeFieldMappings.put("sideFrom", s -> s instanceof Square ? ((Square) s).getSide() : null);
//            shapeFieldMappings.put("sideTo", s -> s instanceof Square ? ((Square) s).getSide() : null);
//            shapeFieldMappings.put("radiusFrom", s -> s instanceof Circle ? ((Circle) s).getRadius() : null);
//            shapeFieldMappings.put("radiusTo", s -> s instanceof Circle ? ((Circle) s).getRadius() : null);
//            shapeFieldMappings.put("heightFrom", s -> s instanceof Rectangle ? ((Rectangle) s).getHeight() : null);
//            shapeFieldMappings.put("heightTo", s -> s instanceof Rectangle ? ((Rectangle) s).getHeight() : null);
//            shapeFieldMappings.put("weightFrom", s -> s instanceof Rectangle ? ((Rectangle) s).getWidth() : null);
//            shapeFieldMappings.put("weightTo", s -> s instanceof Rectangle ? ((Rectangle) s).getWidth() : null);
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
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        };
//    }
//
//}
//
//    public Specification<Shape> buildSpecification(FilterRequest filterRequest) {
//        return (root, query, builder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            // Dodajemy predykaty dla klasy Shape
//            Optional.ofNullable( predicates.add((Predicate) builder.equal(root.get("createdBy"), filterRequest.getCreatedBy())));
//
//            Optional.ofNullable( predicates.add((Predicate) builder.equal(root.get("shapeType"), filterRequest.getShapeType())));
//
////            Optional.ofNullable(predicates.add((Predicate) builder.ge(root.get("area"), filterRequest.getAreaFrom())));
////            Optional.ofNullable(predicates.add((Predicate) builder.le(root.get("area"), filterRequest.getAreaTo())));
////            Optional.ofNullable(predicates.add((Predicate) builder.ge(root.get("perimeter"), filterRequest.getPerimeterFrom())));
////            Optional.ofNullable(predicates.add((Predicate) builder.le(root.get("perimeter"), filterRequest.getPerimeterTo())));
//            Optional.ofNullable(predicates.add((Predicate) builder.greaterThanOrEqualTo(root.get("createdAt"), filterRequest.getCreatedAtFrom())));
//            Optional.ofNullable( predicates.add((Predicate) builder.lessThanOrEqualTo(root.get("createdAt"), filterRequest.getCreatedAtTo())));
//            Optional.ofNullable( predicates.add((Predicate) builder.equal(root.get("version"), filterRequest.getVersion())));
//            Optional.ofNullable( predicates.add((Predicate) builder.greaterThanOrEqualTo(builder.treat(root, Square.class).get("side"), filterRequest.getSideFrom())));
//            Optional.ofNullable(predicates.add((Predicate) builder.greaterThanOrEqualTo(builder.treat(root, Circle.class).get("radius"), filterRequest.getRadiusFrom())));
//            Optional.ofNullable( predicates.add((Predicate) builder.greaterThanOrEqualTo(builder.treat(root, Rectangle.class).get("width"), filterRequest.getWidthFrom())));
//            Optional.ofNullable(predicates.add((Predicate) builder.greaterThanOrEqualTo(builder.treat(root, Rectangle.class).get("height"), filterRequest.getHeightFrom())));
//            Optional.ofNullable( predicates.add((Predicate) builder.greaterThanOrEqualTo(builder.treat(root, Square.class).get("side"), filterRequest.getSideTo())));
//            Optional.ofNullable(predicates.add((Predicate) builder.greaterThanOrEqualTo(builder.treat(root, Circle.class).get("radius"), filterRequest.getRadiusTo())));
//            Optional.ofNullable(predicates.add((Predicate) builder.greaterThanOrEqualTo(builder.treat(root, Rectangle.class).get("width"), filterRequest.getWidthTo())));
//            Optional.ofNullable(predicates.add((Predicate) builder.greaterThanOrEqualTo(builder.treat(root, Rectangle.class).get("height"), filterRequest.getHeightTo())));
//            //            Predicate squarePredicate = (Predicate) builder.and(
////                    builder.equal(builder.treat(root, Square.class).get("side"), filterRequest.getSideFrom())
////            );
//
//            // Sprawdzamy czy istnieją filtry dla klas dziedziczących
////            if (filterRequest.getFilters() != null) {
////                for (Map.Entry<String, Object> entry : filterRequest.getFilters().entrySet()) {
////                    String key = entry.getKey();
////                    Object value = entry.getValue();
//
//                    // Dodajemy predykaty dla klas dziedziczących
////                    switch (key) {
////                        case "sideFrom":
////                            predicates.add(builder.ge(root.get("side"), (Double) value));
////                            break;
////                        case "sideTo":
////                            predicates.add(builder.le(root.get("side"), (Double) value));
////                            break;
////                        case "radiusFrom":
////                            predicates.add(builder.ge(root.get("radius"), (Double) value));
////                            break;
////                        case "radiusTo":
////                            predicates.add(builder.le(root.get("radius"), (Double) value));
////                            break;
////                        default:
////                            // Jeśli nazwa pola nie pasuje, przeskakujemy do kolejnego wpisu
////                            continue;
////                    }
////                }
////            }
//
//            // Tworzymy i zwracamy predykat na podstawie listy predykatów
////            return builder.and(predicates.toArray(new Predicate[0]));
//
//            Optional.ofNullable(filterRequest.getFilters())
//                    .ifPresent(filters -> filters.forEach((key, value) -> {
//                        Expression<Object> field = root.get(key);
//                        if (field.getJavaType() == String.class) {
//                            predicates.add((Predicate) builder.like(field.as(String.class), "%" + value + "%"));
//                        } else {
//                            predicates.add((Predicate) builder.equal(field, value));
//                        }
//                    }));
//
//            query.where(builder.and(predicates.toArray(new javax.persistence.criteria.Predicate[0])));
//
//            TypedQuery<Shape> typedQuery = (TypedQuery<Shape>) entityManager.createQuery(query);
//            return (javax.persistence.criteria.Predicate) typedQuery.getResultList();
//        };
//    }

//    public List<Shape> findFilter() {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> query = criteriaBuilder.createQuery(Shape.class);
//        Root<Shape> root = query.from(Shape.class);
//        Join<Shape, ShapeView> shapeViewJoin = root.join("shapeView");
//
//        Predicate predicate = (Predicate) criteriaBuilder.conjunction();
//
//        UserSearchQueryCriteriaConsumer searchConsumer = new UserSearchQueryCriteriaConsumer(predicate, criteriaBuilder, r);
//        params.stream().forEach(searchConsumer);
//
//        predicate = searchConsumer.getPredicate();
//        query.where(predicate); List<Shape> result = entityManager.createQuery(query).getResultList();
//
//        return result;
//
//
//    }


}