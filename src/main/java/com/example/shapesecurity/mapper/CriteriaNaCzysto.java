package com.example.shapesecurity.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class  CriteriaNaCzysto {

    private final EntityManager entityManager;

//    public List<Shape> filterShapesPrzebudowanie(FilterRequest filterRequest) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> query = criteriaBuilder.createQuery(Shape.class);
//        Root<Shape> root = query.from(Shape.class);
//        Join<Shape, ShapeView> shapeViewJoin = root.join("shapeView");
//
//        List<Predicate> predicates = new ArrayList<>();
//        addPredicateIfNotNullDoPrzykładuWyżej(
//                filterRequest.getSideTo(),
//                "side",
//                predicates,
//                criteriaBuilder,
//                root::get,
//                shape -> shape instanceof Square ? criteriaBuilder.lessThanOrEqualTo(root.get("side"), filterRequest.getSideTo()) : criteriaBuilder.conjunction());
////        addPredicateIfNotNullDoPrzykładuWyżej(filterRequest.getShapeType(), "shapeType", predicates, criteriaBuilder, root::get);
////        addPredicateIfNotNullDoPrzykładuWyżej(filterRequest.getCreatedAtFrom(), "createdAt", predicates, criteriaBuilder, root::get);
////        addPredicateIfNotNullDoPrzykładuWyżej(filterRequest.getCreatedAtTo(), "createdAt", predicates, criteriaBuilder, root::get);
////        addPredicateIfNotNullDoPrzykładuWyżej(filterRequest.getVersion(), "version", predicates, criteriaBuilder, root::get);
////        addPredicateIfNotNullDoPrzykładuWyżej(filterRequest.getSideFrom(), "side", predicates, criteriaBuilder, root::get);
////        addPredicateIfNotNullDoPrzykładuWyżej(filterRequest.getSideTo(), "side", predicates, criteriaBuilder, root::get);
////        addPredicateIfNotNullDoPrzykładuWyżej(filterRequest.getWidthFrom(), "width", predicates, criteriaBuilder, root::get);
////        addPredicateIfNotNullDoPrzykładuWyżej(filterRequest.getWidthTo(), "width", predicates, criteriaBuilder, root::get);
////        addPredicateIfNotNullDoPrzykładuWyżej(filterRequest.getWidthTo(), "width", predicates, criteriaBuilder, root::get,
////                shape -> shape instanceof Square);
////        addPredicateIfNotNullDoPrzykładuWyżej(filterRequest.getHeightTo(), "height", predicates, criteriaBuilder, root::get);
////        addPredicateIfNotNullDoPrzykładuWyżej(filterRequest.getRadiusFrom(), "radius", predicates, criteriaBuilder, root::get);
////        addPredicateIfNotNullDoPrzykładuWyżej(filterRequest.getRadiusTo(), "radius", predicates, criteriaBuilder, root::get);
////        Optional.ofNullable(filterRequest.getAreaFrom())
////                .ifPresent(areaFrom -> predicates.add(criteriaBuilder.greaterThanOrEqualTo(shapeViewJoin.get("area"), areaFrom)));
////        Optional.ofNullable(filterRequest.getAreaTo())
////                .ifPresent(areaTo -> predicates.add(criteriaBuilder.lessThanOrEqualTo(shapeViewJoin.get("area"), areaTo)));
////
////
////        Optional.ofNullable(filterRequest.getFilters())
////                .ifPresent(filters -> filters.forEach((key, value) -> {
////                    Expression<Object> field = root.get(key);
////                    if (field.getJavaType() == String.class) {
////                        predicates.add(criteriaBuilder.like(field.as(String.class), "%" + value + "%"));
////                    } else {
////                        predicates.add(criteriaBuilder.equal(field, value));
////                    }
////                }));
////
////        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
////
////        TypedQuery<Shape> typedQuery = entityManager.createQuery(query);
////        return typedQuery.getResultList();
////    }
////
////    private void addPredicateIfNotNullDoPrzykładuWyżej(Object value, String fieldName, List<Predicate> predicates,
////                                                       CriteriaBuilder criteriaBuilder, Function<String, Expression<Object>> expression,
////                                                       Function<Shape, Predicate> predicateSupplier) {
////        Optional.ofNullable(value)
////                .ifPresent(v -> {
////                    Expression<Object> field = expression.apply(fieldName);
////                    Shape square = new Square();
////                    Predicate predicate = square.getClass().equals(field.getJavaType()) ? predicateSupplier.apply(square) : criteriaBuilder.conjunction();
////                    predicates.add(criteriaBuilder.and(
////                            predicate,
////                            criteriaBuilder.equal(field, v)
////                    ));
////                });
////    }
//    }
    //    private void addPredicateIfNotNullTest(Object fromValue, Object toValue, String fieldName, List<Predicate> predicates,
//                                          CriteriaBuilder criteriaBuilder, Function<String, Expression<Object>> expression) {
//        if (fromValue != null && toValue != null) {
//            predicates.add(criteriaBuilder.between(expression.apply(fieldName), fromValue, toValue));
//        } else if (fromValue != null) {
//            predicates.add(criteriaBuilder.greaterThanOrEqualTo(expression.apply(fieldName), fromValue));
//        } else if (toValue != null) {
//            predicates.add(criteriaBuilder.lessThanOrEqualTo(expression.apply(fieldName), toValue));
//        }
//    }
//
////    private void addPredicateIfNotNull(Object value, String fieldName, List<Predicate> predicates,
////                                       CriteriaBuilder criteriaBuilder, Function<String, Expression<Object>> expression) {
////        Optional.ofNullable(value)
////                .ifPresent(v -> predicates.add(criteriaBuilder.equal(expression.apply(fieldName), v)));
////    }
//
//    public List<Shape> filterShapes333(FilterRequest filterRequest) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> query = criteriaBuilder.createQuery(Shape.class);
//        Root<Shape> root = query.from(Shape.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//
//        addPredicateIfNotNull333(filterRequest.getCreatedBy(), "createdBy", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull333(filterRequest.getShapeType(), "shapeType", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull333(filterRequest.getCreatedAtFrom(), "createdAt", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull333(filterRequest.getCreatedAtTo(), "createdAt", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull333(filterRequest.getVersion(), "version", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull333(filterRequest.getSideFrom(), "side", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull333(filterRequest.getSideTo(), "side", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull333(filterRequest.getWidthFrom(), "width", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull333(filterRequest.getWidthTo(), "width", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull333(filterRequest.getHeightFrom(), "height", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull333(filterRequest.getHeightTo(), "height", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull333(filterRequest.getRadiusFrom(), "radius", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull333(filterRequest.getRadiusTo(), "radius", predicates, criteriaBuilder, root::get);
//
//        // join with ShapeView entity and filter by area
//        addPredicateIfNotNull(filterRequest.getAreaFrom(), "areaFrom", predicates, criteriaBuilder,
//                fieldName -> root.join("shapeView", JoinType.INNER).get("area"));
//        addPredicateIfNotNull(filterRequest.getAreaTo(), "areaTo", predicates, criteriaBuilder,
//                fieldName -> root.join("shapeView", JoinType.INNER).get("area"));
//
//        Optional.ofNullable(filterRequest.getFilters())
//                .ifPresent(filters -> filters.forEach((key, value) -> {
//                    Expression<Object> field = root.get(key);
//                    if (field.getJavaType() == String.class) {
//                        predicates.add(criteriaBuilder.like(field.as(String.class), "%" + value + "%"));
//                    } else {
//                        predicates.add(criteriaBuilder.equal(field, value));
//                    }
//                }));
//
//        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
//
//        TypedQuery<Shape> typedQuery = entityManager.createQuery(query);
//        return typedQuery.getResultList();
//    }
//    private void addPredicateIfNotNull333(Object fromValue, Object toValue, String fieldName, List<Predicate> predicates,
//                                       CriteriaBuilder criteriaBuilder, Function<String, Expression<Object>> expression) {
//        if (fromValue != null && toValue != null) {
//            predicates.add(criteriaBuilder.between(expression.apply(fieldName), fromValue, toValue));
//        } else if (fromValue != null) {
//            predicates.add(criteriaBuilder.greaterThanOrEqualTo(expression.apply(fieldName), fromValue));
//        } else if (toValue != null) {
//            predicates.add(criteriaBuilder.lessThanOrEqualTo(expression.apply(fieldName), toValue));
//        }
//    }
//    private void addPredicateIfNotNull33(Object value, String fieldName, List<Predicate> predicates,
//                                       CriteriaBuilder criteriaBuilder, Function<String, Expression<Object>> expression) {
//        Optional.ofNullable(value)
//                .ifPresent(v -> {
//                    if (fieldName.endsWith("From")) {
//                        predicates.add(criteriaBuilder.greaterThanOrEqualTo(expression.apply(fieldName), v));
//                    } else if (fieldName.endsWith("To")) {
//                        predicates.add(criteriaBuilder.lessThanOrEqualTo(expression.apply(fieldName), v));
//                    } else {
//                        predicates.add(criteriaBuilder.equal(expression.apply(fieldName), v));
//                    }
//                });
//    }
//
//
//    public List<Shape> filterShapes32(FilterRequest filterRequest) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> query = criteriaBuilder.createQuery(Shape.class);
//        Root<Shape> root = query.from(Shape.class);
//
//        List<Predicate> predicates = new ArrayList<>();
//
//        addPredicateIfNotNull2(filterRequest.getCreatedBy(), "createdBy", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull2(filterRequest.getShapeType(), "shapeType", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull2(filterRequest.getCreatedAtFrom(), "createdAt", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull2(filterRequest.getCreatedAtTo(), "createdAt", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull2(filterRequest.getVersion(), "version", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull2(filterRequest.getSideFrom(), "side", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull2(filterRequest.getSideTo(), "side", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull2(filterRequest.getWidthFrom(), "width", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull2(filterRequest.getWidthTo(), "width", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull2(filterRequest.getHeightFrom(), "height", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull2(filterRequest.getHeightTo(), "height", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull2(filterRequest.getRadiusFrom(), "radius", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull2(filterRequest.getRadiusTo(), "radius", predicates, criteriaBuilder, root::get);
//
//
//
//        // join with ShapeType entity and filter by name
//        addPredicateIfNotNull(filterRequest.getAreaFrom(), "area", predicates, criteriaBuilder,
//                fieldName -> root.join("shapeView", JoinType.INNER).get("area"));
//
//        Optional.ofNullable(filterRequest.getFilters())
//                .ifPresent(filters -> filters.forEach((key, value) -> {
//                    Expression<Object> field = root.get(key);
//                    if (field.getJavaType() == String.class) {
//                        predicates.add(criteriaBuilder.like(field.as(String.class), "%" + value + "%"));
//                    } else {
//                        predicates.add(criteriaBuilder.equal(field, value));
//                    }
//                }));
//
//        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
//
//        TypedQuery<Shape> typedQuery = entityManager.createQuery(query);
//        return typedQuery.getResultList();
//    }
//
//    private void addPredicateIfNotNull2(Object value, String fieldName, List<Predicate> predicates,
//                                        CriteriaBuilder criteriaBuilder, Function<String, Expression<Object>> expression,
//                                        boolean isFrom) {
//        Optional.ofNullable(value)
//                .ifPresent(v -> {
//                    Expression<Object> field = expression.apply(fieldName);
//                    Predicate predicate = isFrom ? criteriaBuilder.greaterThanOrEqualTo(field, v)
//                            : criteriaBuilder.lessThanOrEqualTo(field, v);
//                    predicates.add(predicate);
////                });
////    }
//// to działa ale ogarnąć trzeba żeby filtrowało od do
//    public List<Shape> filterShapes3(FilterRequest filterRequest) {
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> query = criteriaBuilder.createQuery(Shape.class);
//        Root<Shape> root = query.from(Shape.class);
//
//
//        List<Predicate> predicates = new ArrayList<>();
//
//
////        if (filterRequest.getSideFrom() == null) {
////            Predicate predicate = criteriaBuilder.equal(root.get("side"), Square.class.getSimpleName());
////            predicates.add(predicate);
////        }
//
////        addPredicateIfNotNull(criteriaBuilder.greaterThanOrEqualTo(root.get("side"), Square.class.getSimpleName()));
//        addPredicateIfNotNull(filterRequest.getCreatedBy(), "createdBy", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull(filterRequest.getShapeType(), "shapeType", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull(filterRequest.getCreatedAtFrom(), "createdAt", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull(filterRequest.getCreatedAtTo(), "createdAt", predicates, criteriaBuilder, root::get);
//        addPredicateIfNotNull(filterRequest.getVersion(), "version", predicates, criteriaBuilder, root::get);
////        addPredicateIfNotNull(filterRequest.getSideFrom(), "side", predicates, criteriaBuilder, root::get);
////        addPredicateIfNotNull(filterRequest.getSideTo(), "side", predicates, criteriaBuilder, root::get);
////        addPredicateIfNotNull(filterRequest.getWidthFrom(), "width", predicates, criteriaBuilder, root::get);
////        addPredicateIfNotNull(filterRequest.getWidthTo(), "width", predicates, criteriaBuilder, root::get);
////        addPredicateIfNotNull(filterRequest.getHeightFrom(), "height", predicates, criteriaBuilder, root::get);
////        addPredicateIfNotNull(filterRequest.getHeightTo(), "height", predicates, criteriaBuilder, root::get);
////        addPredicateIfNotNull(filterRequest.getRadiusFrom(), "radius", predicates, criteriaBuilder, root::get);
////        addPredicateIfNotNull(filterRequest.getRadiusTo(), "radius", predicates, criteriaBuilder, root::get);
//        Optional.of(predicates.add((Predicate) criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.treat(root, Square.class).get("side"), filterRequest.getSideFrom())));
//        Optional.of(predicates.add((Predicate) criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.treat(root, Circle.class).get("radius"), filterRequest.getRadiusFrom())));
//        Optional.of(predicates.add((Predicate) criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.treat(root, Rectangle.class).get("width"), filterRequest.getWidthFrom())));
//        Optional.of(predicates.add((Predicate) criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.treat(root, Rectangle.class).get("height"), filterRequest.getHeightFrom())));
//        Optional.of(predicates.add((Predicate) criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.treat(root, Square.class).get("side"), filterRequest.getSideTo())));
//        Optional.of(predicates.add((Predicate) criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.treat(root, Circle.class).get("radius"), filterRequest.getRadiusTo())));
//        Optional.of(predicates.add((Predicate) criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.treat(root, Rectangle.class).get("width"), filterRequest.getWidthTo())));
//        Optional.of(predicates.add((Predicate) criteriaBuilder.greaterThanOrEqualTo(criteriaBuilder.treat(root, Rectangle.class).get("height"), filterRequest.getHeightTo())));
//
//// Optional.ofNullable( predicates.add((Predicate) builder.greaterThanOrEqualTo(builder.treat(root, Square.class).get("side"), filterRequest.getSideFrom())));
////            Optional.ofNullable(predicates.add((Predicate) builder.greaterThanOrEqualTo(builder.treat(root, Circle.class).get("radius"), filterRequest.getRadiusFrom())));
////            Optional.ofNullable( predicates.add((Predicate) builder.greaterThanOrEqualTo(builder.treat(root, Rectangle.class).get("width"), filterRequest.getWidthFrom())));
////            Optional.ofNullable(predicates.add((Predicate) builder.greaterThanOrEqualTo(builder.treat(root, Rectangle.class).get("height"), filterRequest.getHeightFrom())));
////            Optional.ofNullable( predicates.add((Predicate) builder.greaterThanOrEqualTo(builder.treat(root, Square.class).get("side"), filterRequest.getSideTo())));
////            Optional.ofNullable(predicates.add((Predicate) builder.greaterThanOrEqualTo(builder.treat(root, Circle.class).get("radius"), filterRequest.getRadiusTo())));
////            Optional.ofNullable(predicates.add((Predicate) builder.greaterThanOrEqualTo(builder.treat(root, Rectangle.class).get("width"), filterRequest.getWidthTo())));
////            Optional.ofNullable(predicates.add((Predicate) builder.greaterThanOrEqualTo(builder.treat(root, Rectangle.class).get("height"), filterRequest.getHeightTo())));
//        // join with ShapeType entity and filter by name
//        addPredicateIfNotNull(filterRequest.getAreaFrom(), "area", predicates, criteriaBuilder,
//                fieldName -> root.join("shapeView", JoinType.INNER).get("area"));
//
//
//        Optional.ofNullable(filterRequest.getFilters())
//                .ifPresent(filters -> filters.forEach((key, value) -> {
//                    Expression<Object> field = root.get(key);
//                    if (field.getJavaType() == String.class) {
//                        predicates.add(criteriaBuilder.like(field.as(String.class), "%" + value + "%"));
//                    } else {
//                        predicates.add(criteriaBuilder.equal(field, value));
//                    }
//                }));
//
//        query.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
//
//        TypedQuery<Shape> typedQuery = entityManager.createQuery(query);
//        return typedQuery.getResultList();
//    }
//    private void addPredicateIfNotNull(Object value, String fieldName, List<Predicate> predicates,
//                                       CriteriaBuilder criteriaBuilder, Function<String, Expression<Object>> expression) {
//        Optional.ofNullable(value)
//                .ifPresent(v -> predicates.add(criteriaBuilder.equal(expression.apply(fieldName), v)));
//    }

//    private void addPredicateIfNotNullTest(Object value, String fieldName, List<Predicate> predicates,
//                                       CriteriaBuilder criteriaBuilder, Function<String, Expression<Object>> expression,
//                                       String relation) {
//        Optional.ofNullable(value)
//                .ifPresent(v -> {
//                    Expression<Object> field = expression.apply(fieldName);
//                    switch (relation) {
//                        case "EQUAL":
//                            predicates.add(criteriaBuilder.equal(field, v));
//                            break;
//                        case "GREATER_THAN_OR_EQUAL":
//                            predicates.add(criteriaBuilder.greaterThanOrEqualTo(field, (Number) v));
//                            break;
//                        case "LESS_THAN_OR_EQUAL":
//                            predicates.add(criteriaBuilder.lessThanOrEqualTo(field, (Number) v));
//                            break;
//                    }
//                });
////    }
//
//    public Specification<Shape> filterShape2(FilterRequest filterRequest) {
//        return (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            addPredicateIfNotNull(filterRequest.getCreatedBy(), "createdBy", predicates, criteriaBuilder, root::get);
////            addPredicateIfNotNull(filterRequest.getAreaFrom(), "area", predicates, criteriaBuilder, root::get);
////            addPredicateIfNotNull(filterRequest.getAreaTo(), "area", predicates, criteriaBuilder, root::get);
////            addPredicateIfNotNull(filterRequest.getPerimeterFrom(), "perimeter", predicates, criteriaBuilder, root::get);
////            addPredicateIfNotNull(filterRequest.getPerimeterTo(), "perimeter", predicates, criteriaBuilder, root::get);
////            addPredicateIfNotNull(filterRequest.getCreatedAtFrom(), "createdAt", predicates, criteriaBuilder, root::get);
////            addPredicateIfNotNull(filterRequest.getCreatedAtTo(), "createdAt", predicates, criteriaBuilder, root::get);
////            addPredicateIfNotNull(filterRequest.getVersion(), "version", predicates, criteriaBuilder, root::get);
//
//            // join with ShapeType entity and filter by name
//            addPredicateIfNotNull(filterRequest.getAreaFrom(), "area", predicates, criteriaBuilder,
//                    fieldName -> root.join("SHAPE", JoinType.INNER).get("area"));
//
//            Optional.ofNullable(filterRequest.getFilters())
//                    .ifPresent(filters -> filters.forEach((key, value) -> {
//                        Expression<Object> field = root.get(key);
//                        if (field.getJavaType() == String.class) {
//                            predicates.add(criteriaBuilder.like(field.as(String.class), "%" + value + "%"));
//                        } else {
//                            predicates.add(criteriaBuilder.equal(field, value));
//                        }
//                    }));
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        };
//    }
//
//
//    public Specification<Shape> filterShape(FilterRequest filterRequest) {
//        return (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            addPredicateIfNotNull(filterRequest.getCreatedBy(), "createdBy", predicates, criteriaBuilder, root::get);
//            addPredicateIfNotNull(filterRequest.getShapeType(), "type", predicates, criteriaBuilder, root::get);
//            addPredicateIfNotNull(filterRequest.getAreaFrom(), "area", predicates, criteriaBuilder, root::get);
//            addPredicateIfNotNull(filterRequest.getAreaTo(), "area", predicates, criteriaBuilder, root::get);
//            addPredicateIfNotNull(filterRequest.getPerimeterFrom(), "perimeter", predicates, criteriaBuilder, root::get);
//            addPredicateIfNotNull(filterRequest.getPerimeterTo(), "perimeter", predicates, criteriaBuilder, root::get);
//            addPredicateIfNotNull(filterRequest.getCreatedAtFrom(), "createdAt", predicates, criteriaBuilder, root::get);
//            addPredicateIfNotNull(filterRequest.getCreatedAtTo(), "createdAt", predicates, criteriaBuilder, root::get);
//            addPredicateIfNotNull(filterRequest.getVersion(), "version", predicates, criteriaBuilder, root::get);
//
//            Optional.ofNullable(filterRequest.getFilters())
//                    .ifPresent(filters -> filters.forEach((key, value) -> {
//                        Expression<Object> field = root.get(key);
//                        if (field.getJavaType() == String.class) {
//                            predicates.add(criteriaBuilder.like(field.as(String.class), "%" + value + "%"));
//                        } else {
//                            predicates.add(criteriaBuilder.equal(field, value));
//                        }
//                    }));
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        };
//    }
//
//    private void addPredicateIfNotNull2(Object value, String fieldName, List<Predicate> predicates,
//                                        CriteriaBuilder criteriaBuilder, Function<String, Expression<Object>> expression) {
//        Optional.ofNullable(value)
//                .ifPresent(v -> predicates.add(criteriaBuilder.equal(expression.apply(fieldName), v)));
//    }
////    public Specification<Shape> createSpecification33(FilterRequest filterRequest) {
////        return (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            addPredicateIfNotNull(filterRequest.getCreatedBy(), "createdBy", predicates, criteriaBuilder, root::get);
//            addPredicateIfNotNull(filterRequest.getShapeType(), "type", predicates, criteriaBuilder, root::get);
//            addPredicateIfNotNull(filterRequest.getAreaFrom(), "area", predicates, criteriaBuilder, root::get);
//            addPredicateIfNotNull(filterRequest.getAreaTo(), "area", predicates, criteriaBuilder, root::get);
//            addPredicateIfNotNull(filterRequest.getPerimeterFrom(), "perimeter", predicates, criteriaBuilder, root::get);
//            addPredicateIfNotNull(filterRequest.getPerimeterTo(), "perimeter", predicates, criteriaBuilder, root::get);
//            addPredicateIfNotNull(filterRequest.getCreatedAtFrom(), "createdAt", predicates, criteriaBuilder, root::get);
//            addPredicateIfNotNull(filterRequest.getCreatedAtTo(), "createdAt", predicates, criteriaBuilder, root::get);
//            addPredicateIfNotNull(filterRequest.getVersion(), "version", predicates, criteriaBuilder, root::get);
//
//            Optional.ofNullable(filterRequest.getFilters())
//                    .ifPresent(filters -> filters.forEach((key, value) -> {
//                        Expression<Object> field = root.get(key);
//                        if (field.getJavaType() == String.class) {
//                            predicates.add(criteriaBuilder.like((Expression<String>) field, "%" + value + "%"));
//                        } else {
//                            predicates.add((Predicate) criteriaBuilder.equal(field, value));
//                        }
//                    }));
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        };
//    }
//    private <T> void addPredicateIfNotNull(T value, String fieldName, List<Predicate> predicates,
//                                           CriteriaBuilder criteriaBuilder, Function<String, Path<T>> path) {
//        Optional.ofNullable(value)
//                .ifPresent(v -> predicates.add((Predicate) criteriaBuilder.equal(path.apply(fieldName), v)));
//    }
//    private <T> void addPredicateIfNotNull(T value, String fieldName, List<Predicate> predicates,
//                                           CriteriaBuilder criteriaBuilder, Function<String, Expression<T>> expression) {
//        Optional.ofNullable(value)
//                .ifPresent(v -> predicates.add(criteriaBuilder.equal(expression.apply(fieldName), v)));
//    }

//    public Specification<Shape> createSpecification2(FilterRequest filterRequest) {
//        return (root, query, criteriaBuilder) -> {
//            List<Predicate> predicates = new ArrayList<>();
//
//            Optional.ofNullable(filterRequest.getCreatedBy())
//                    .ifPresent(createdBy -> predicates.add((Predicate) criteriaBuilder.equal(root.get("createdBy"), createdBy)));
//            Optional.ofNullable(filterRequest.getShapeType())
//                    .ifPresent(shapeType -> predicates.add((Predicate) criteriaBuilder.equal(root.get("type"), shapeType)));
//            Optional.ofNullable(filterRequest.getAreaFrom())
//                    .ifPresent(areaFrom -> predicates.add((Predicate) criteriaBuilder.greaterThanOrEqualTo(root.get("area"), areaFrom)));
//            Optional.ofNullable(filterRequest.getAreaTo())
//                    .ifPresent(areaTo -> predicates.add((Predicate) criteriaBuilder.lessThanOrEqualTo(root.get("area"), areaTo)));
//            Optional.ofNullable(filterRequest.getPerimeterFrom())
//                    .ifPresent(perimeterFrom -> predicates.add((Predicate) criteriaBuilder.greaterThanOrEqualTo(root.get("perimeter"), perimeterFrom)));
//            Optional.ofNullable(filterRequest.getPerimeterTo())
//                    .ifPresent(perimeterTo -> predicates.add((Predicate) criteriaBuilder.lessThanOrEqualTo(root.get("perimeter"), perimeterTo)));
//            Optional.ofNullable(filterRequest.getCreatedAtFrom())
//                    .ifPresent(createdAtFrom -> predicates.add((Predicate) criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), createdAtFrom)));
//            Optional.ofNullable(filterRequest.getCreatedAtTo())
//                    .ifPresent(createdAtTo -> predicates.add((Predicate) criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), createdAtTo)));
//            Optional.ofNullable(filterRequest.getVersion())
//                    .ifPresent(version -> predicates.add((Predicate) criteriaBuilder.equal(root.get("version"), version)));
//            Optional.ofNullable(filterRequest.getFilters())
//                    .ifPresent(filters -> filters.forEach((key, value) -> {
//                        Expression<Object> field = root.get(key);
//                        if (field.getJavaType() == String.class) {
//                            predicates.add(criteriaBuilder.like((Expression<String>) field, "%" + value + "%"));
//                        } else {
//                            predicates.add((Predicate) criteriaBuilder.equal(field, value));
//                        }
//                    }));
//
//            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        };
//    }
//
//    public List<Shape> filtruj(FilterRequest filterRequest){
//        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> query = criteriaBuilder.createQuery(Shape.class);
//        Root<Shape> shapeRoot = query.from(Shape.class);
//        Join<Shape, ShapeView> shapeViewJoin = shapeRoot.join("shapeView");
//
//        List<Predicate> predicates = new ArrayList<>();
//        Optional.ifPresent(minAge -> predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("age"), minAge)));
//        Optional.ofNullable(filterRequest.getAreaFrom())
//                .ifPresent(n -> predicates.add(criteriaBuilder.like(shapeRoot.get("area"), "%" + n + "%")));
//        Optional.ofNullable(age)
//                .ifPresent(a -> predicates.add(criteriaBuilder.equal(root.get("age"), a)));
//        Optional.ofNullable(address)
//                .ifPresent(ad -> predicates.add(criteriaBuilder.like(root2.get("address"), "%" + ad + "%")));
//        Optional.ofNullable(phone)
//                .ifPresent(p -> predicates.add(criteriaBuilder.equal(root2.get("phone"), p)));
//
//        criteria.where(criteriaBuilder.and(predicates.toArray(new Predicate[0])));
//
//        //Optional<Integer> optionalMinAge = Optional.ofNullable(20); // minimalny wiek użytkownika
//        //Optional<Integer> optionalMaxAge = Optional.ofNullable(40); // maksymalny wiek użytkownika
//        //
//        //CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//        //CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
//        //Root<User> root = query.from(User.class);
//        //query.select(root);
//        //
//        //List<Predicate> predicates = new ArrayList<>();
//        //optionalMinAge.ifPresent(minAge -> predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("age"), minAge)));
//        //optionalMaxAge.ifPresent(maxAge -> predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("age"), maxAge)));
//        //
//        //Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));
//        //
//        //query.where(finalPredicate);
//        //
//        //List<User> users = session.createQuery(query).getResultList();
//
//    }
//
//
//    public List<Shape> filter(FilterRequest filterRequest) {
//
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> query = cb.createQuery(Shape.class);
//
//        Root<Shape> shapeRoot = query.from(Shape.class);
//        Join<Shape, ShapeView> shapeViewJoin = shapeRoot.join("shapeView");
//
//        List<Predicate> predicatesMap = new ArrayList<>();
//        predicatesMap.add((Predicate) cb.between(shapeViewJoin.get("area"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
//        predicatesMap.add((Predicate) cb.between(shapeViewJoin.get("perimeter"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
//        List<Predicate> predicates = new ArrayList<>();
//
//        Map<String, Predicate<Shape>> predicatesMap = new HashMap<>();
////        predicatesMap.put("createdBy", s -> filterRequest.getCreatedBy() == null || filterRequest.getCreatedBy().equals(s.getCreatedBy()));
////        predicatesMap.put("createdBy", s -> filterRequest.getCreatedBy() == null || filterRequest.getCreatedBy().equals(s.getCreatedBy()));
//
//        predicatesMap.put("createdBy", s -> filterRequest.getCreatedBy() == null || filterRequest.getCreatedBy().equals(s.getCreatedBy()));
//        predicatesMap.put("shapeType", s -> filterRequest.getShapeType() == null || filterRequest.getShapeType().equals(s.getType()));
//        predicatesMap.put("areaFrom", s -> filterRequest.getAreaFrom() == null || filterRequest.getAreaFrom() <= s.computeArea());
//        predicatesMap.put("areaTo", s -> filterRequest.getAreaTo() == null || filterRequest.getAreaTo() >= s.computeArea());
//        predicatesMap.put("perimeterFrom", s -> filterRequest.getPerimeterFrom() == null || filterRequest.getPerimeterFrom() <= s.computePerimeter());
//        predicatesMap.put("perimeterTo", s -> filterRequest.getPerimeterTo() == null || filterRequest.getPerimeterTo() >= s.computePerimeter());
//        predicatesMap.put("createdAtFrom", s -> filterRequest.getCreatedAtFrom() == null || filterRequest.getCreatedAtFrom().isBefore(s.getCreatedAt()));
//        predicatesMap.put("createdAtTo", s -> filterRequest.getCreatedAtTo() == null || filterRequest.getCreatedAtTo().isAfter(s.getCreatedAt()));
//        predicatesMap.put("version", s -> filterRequest.getVersion() == null || filterRequest.getVersion().equals(s.getVersion()));
//        predicatesMap.put("sideFrom", s -> !(s instanceof Rectangle) || filterRequest.getSideFrom() == null || filterRequest.getSideFrom() <= ((Square) s).getSide());
//        predicatesMap.put("sideTo", s -> !(s instanceof Rectangle) || filterRequest.getSideTo() == null || filterRequest.getSideTo() >= ((Square) s).getSide());
//        predicatesMap.put("widthFrom", s -> !(s instanceof Rectangle) || filterRequest.getWidthFrom() == null || filterRequest.getWidthFrom() <= ((Rectangle) s).getWidth());
//        predicatesMap.put("widthTo", s -> !(s instanceof Rectangle) || filterRequest.getWidthTo() == null || filterRequest.getWidthTo() >= ((Rectangle) s).getWidth());
//        predicatesMap.put("heightFrom", s -> !(s instanceof Rectangle) || filterRequest.getHeightFrom() == null || filterRequest.getHeightFrom() <= ((Rectangle) s).getHeight());
//        predicatesMap.put("heightTo", s -> !(s instanceof Rectangle) || filterRequest.getHeightTo() == null || filterRequest.getHeightTo() >= ((Rectangle) s).getHeight());
//        predicatesMap.put("radiusFrom", s -> !(s instanceof Circle) || filterRequest.getRadiusFrom() == null || filterRequest.getRadiusFrom() <= ((Circle) s).getRadius());
//        predicatesMap.put("radiusTo", s -> !(s instanceof Circle) || filterRequest.getRadiusTo() == null || filterRequest.getRadiusTo() >= ((Circle) s).getRadius());
//
//        for (Map.Entry<String, Object> entry : filterRequest.getFilters().entrySet()) {
//            String attributeName = entry.getKey();
//            Predicate<Shape> predicate = predicatesMap.get(attributeName);
//            if (predicate != null) {
//                Object value = entry.getValue();
//                if (value instanceof Shape) {
//                    predicates.add((Predicate<Shape>) value);
//                } else {
//                    predicates.add(((Predicate<Object>) predicate).isEqual(value));
//                }
//            }
//        }
//
//        query.where(cb.and(predicates.toArray(new Predicate[0])));
//
//        TypedQuery<Shape> typedQuery = entityManager.createQuery(query);
//        return typedQuery.getResultList();
//    }
//
//        public List<ShapeDto> filter2(FilterRequest filterRequest) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> cq = cb.createQuery(Shape.class);
//
//        Root<Shape> shapes = cq.from(Shape.class);
//        Join<Shape, ShapeView> shapeViewJoin = shapes.join("shapeView");
//
//        List<Predicate> predicates = new ArrayList<>();
//        if (filterRequest.getAreaFrom() != null) {
//            predicates.add((Predicate) cb.between(shapeViewJoin.get("area"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
//        }
//        if (filterRequest.getPerimeterFrom() != null) {
//            predicates.add((Predicate) cb.between(shapeViewJoin.get("perimeter"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
//        }
//
//        if (filterRequest.getCreatedBy() != null) {
//            predicates.add((Predicate) cb.between(shapes.get("createdBy"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
//
//        }
//        if (filterRequest.getShapeType() != null) {
//            predicates.add((Predicate) cb.equal(shapes.get("type"), filterRequest.getShapeType()));
//        }
//
//        if (filterRequest.getCreatedAtFrom() != null) {
//            predicates.add((Predicate) cb.greaterThanOrEqualTo(shapes.get("createdAt"), filterRequest.getCreatedAtFrom()));
//        }
//        if (filterRequest.getCreatedAtTo() != null) {
//            predicates.add((Predicate) cb.lessThanOrEqualTo(shapes.get("createdAt"), filterRequest.getCreatedAtTo()));
//        }
//        if (filterRequest.getVersion() != null) {
//            predicates.add((Predicate) cb.equal(shapes.get("version"), filterRequest.getVersion()));
//        }
//        if (filterRequest.getSideFrom() != null) {
//            predicates.add((Predicate) cb.greaterThanOrEqualTo(shapes.get("side"), filterRequest.getSideFrom()));
//        }
//        if (filterRequest.getSideTo() != null) {
//            predicates.add((Predicate) cb.lessThanOrEqualTo(shapes.get("side"), filterRequest.getSideTo()));
//        }
//        if (filterRequest.getWidthFrom() != null) {
//            predicates.add((Predicate) cb.greaterThanOrEqualTo(shapes.get("width"), filterRequest.getWidthFrom()));
//        }
//        if (filterRequest.getWidthTo() != null) {
//            predicates.add((Predicate) cb.lessThanOrEqualTo(shapes.get("width"), filterRequest.getWidthTo()));
//        }
//        if (filterRequest.getHeightFrom() != null) {
//            predicates.add((Predicate) cb.greaterThanOrEqualTo(shapes.get("height"), filterRequest.getHeightFrom()));
//        }
//        if (filterRequest.getHeightTo() != null) {
//            predicates.add((Predicate) cb.lessThanOrEqualTo(shapes.get("height"), filterRequest.getHeightTo()));
//        }
//        if (filterRequest.getRadiusFrom() != null) {
//            predicates.add((Predicate) cb.greaterThanOrEqualTo(shapes.get("radius"), filterRequest.getRadiusFrom()));
//        }
//        if (filterRequest.getRadiusTo() != null) {
//            predicates.add((Predicate) cb.lessThanOrEqualTo(shapes.get("radius"), filterRequest.getRadiusTo()));
//        }
//
////        cq.where(cb.and((javax.persistence.criteria.Predicate) predicates.toArray(new Predicate[0])));
//        TypedQuery<Shape> query = entityManager.createQuery(cq);
//
//
//        return query.getResultList().stream()
//                .map(shape -> new ShapeDto(shape.getId(), shape.getType(), shape.getVersion(), shape.getCreatedBy(), shape.getCreatedAt().toString(),
//                        shape.getLastModifiedAt().toString(), shape.getLastModifiedBy(), shape.computeArea(), shape.computePerimeter()))
//                .collect(Collectors.toList());
//    }


}
