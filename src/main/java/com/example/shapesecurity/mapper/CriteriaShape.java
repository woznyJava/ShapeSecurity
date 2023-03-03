package com.example.shapesecurity.mapper;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.model.shape.ShapeView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class CriteriaShape {

    private final EntityManager entityManager;

    public List<Shape> getShapesByAreaAndPerimeter(FilterRequest filterRequest) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shape> query = cb.createQuery(Shape.class);
        Root<Shape> shapeRoot = query.from(Shape.class);
        Join<Shape, ShapeView> shapeViewJoin = shapeRoot.join("shapeView");

        List<Predicate> predicates = new ArrayList<>();
//        predicates.add("createdBy", s -> filterRequest.getCreatedBy() == null || filterRequest.getCreatedBy().equals());

        predicates.add(cb.between(shapeViewJoin.get("area"),  filterRequest.getAreaFrom() , filterRequest.getAreaTo()));
        predicates.add(cb.between(shapeViewJoin.get("perimeter"), filterRequest.getPerimeterFrom(), filterRequest.getPerimeterTo()));
        predicates.add(cb.between(shapeRoot.get("perimeter"), filterRequest.getPerimeterFrom(), filterRequest.getPerimeterTo()));

        query.select(shapeRoot).where(predicates.toArray(new Predicate[]{}));

        return entityManager.createQuery(query).getResultList();
    }
}
//    public List<ShapeDto> filter33(FilterRequest filterRequest) {
//    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//    CriteriaQuery<Shape> cq = cb.createQuery(Shape.class);
//    Root<Shape> shapeRoot = cq.from(Shape.class);
//    Join<Shape, ShapeView> shapeViewJoin = shapeRoot.join("shapeView");
//
//    // tworzymy listę predykatów
//    List<Predicate> predicates = new ArrayList<>();
//
//// filtrujemy ShapeView na podstawie kryteriów
//predicates.add(cb.between(shapeViewJoin.get("area"),  filterRequest.getAreaFrom(), filterRequest.getAreaTo()));));
//predicates.add(cb.between(shapeViewJoin.get("perimeter"), filterRequest.getPerimeterFrom(), filterRequest.getPerimeterTo()));
//
//    // łączymy Shape i ShapeView za pomocą predykatów
//    Predicate predicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
//cq.where(predicate);
//
//    // wykonujemy zapytanie i zwracamy wyniki
//    TypedQuery<Shape> query = entityManager.createQuery(cq);
//    List<Shape> result = query.getResultList();
//return result;
//
//    //        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
////        CriteriaQuery<Shape> query = cb.createQuery(Shape.class);
////        Root<Shape> shapeRoot = query.from(Shape.class);
////        Join<Shape, ShapeView> shapeViewJoin = shapeRoot.join("shapeView");
////
////        List<Predicate> predicatesMap = new ArrayList<>();
////        predicatesMap.add(cb.between(shapeViewJoin.get("area"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
////        predicatesMap.add(cb.between(shapeViewJoin.get("perimeter"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
////
////        query.select(shapeRoot).
////
////                where(predicatesMap.toArray(new Predicate[]{
////                }));
////
////        List<Shape> shapes = entityManager.createQuery(query).getResultList();
////
////        return shapes.stream()
////                .map(shape -> new ShapeDto(shape.getId(), shape.getType(), shape.getVersion(), shape.getCreatedBy(), shape.getCreatedAt().toString(),
////                        shape.getLastModifiedAt().toString(), shape.getLastModifiedBy(), shape.computeArea(), shape.computePerimeter()))
////                .collect(Collectors.toList());
////    }
//    public List<ShapeDto> filter2(FilterRequest filterRequest) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> cq = cb.createQuery(Shape.class);
//        Root<Shape> shapes = cq.from(Shape.class);
//        Join<Shape, ShapeView> shapeViewJoin = shapes.join("shapeView");
//
////        List<Predicate> predicatesMap = new ArrayList<>();
////        predicatesMap.add((Predicate) cb.between(shapeViewJoin.get("area"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
////        predicatesMap.add((Predicate) cb.between(shapeViewJoin.get("perimeter"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
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
////        cq.where(cb.and(predicates.toArray(new Predicate[0])));
//        TypedQuery<Shape> query = entityManager.createQuery(cq);
//
//
//        return query.getResultList().stream()
//                .map(shape -> new ShapeDto(shape.getId(), shape.getType(), shape.getVersion(), shape.getCreatedBy(), shape.getCreatedAt().toString(),
//                        shape.getLastModifiedAt().toString(), shape.getLastModifiedBy(), shape.computeArea(), shape.computePerimeter()))
//                .collect(Collectors.toList());
//    }
//
//    public List<Shape> filter(FilterRequest filterRequest) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> query = cb.createQuery(Shape.class);
//        Root<Shape> root = query.from(Shape.class);
//        //        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
////        CriteriaQuery<Shape> query = cb.createQuery(Shape.class);
//        Root<Shape> shapeRoot = query.from(Shape.class);
//        Join<Shape, ShapeView> shapeViewJoin = shapeRoot.join("shapeView");
////
//        List<Predicate> predicatesMap = new ArrayList<>();
//        predicatesMap.add((Predicate) cb.between(shapeViewJoin.get("area"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
//        predicatesMap.add((Predicate) cb.between(shapeViewJoin.get("perimeter"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
////        List<Predicate> predicates = new ArrayList<>();
//
////        Map<String, Predicate<Shape>> predicatesMap = new HashMap<>();
////        predicatesMap.put("createdBy", s -> filterRequest.getCreatedBy() == null || filterRequest.getCreatedBy().equals(s.getCreatedBy()));
////        predicatesMap.put("shapeType", s -> filterRequest.getShapeType() == null || filterRequest.getShapeType().equals(s.getType()));
////        predicatesMap.put("areaFrom", s -> filterRequest.getAreaFrom() == null || filterRequest.getAreaFrom() <= s.computeArea());
////        predicatesMap.put("areaTo", s -> filterRequest.getAreaTo() == null || filterRequest.getAreaTo() >= s.computeArea());
////        predicatesMap.put("perimeterFrom", s -> filterRequest.getPerimeterFrom() == null || filterRequest.getPerimeterFrom() <= s.computePerimeter());
////        predicatesMap.put("perimeterTo", s -> filterRequest.getPerimeterTo() == null || filterRequest.getPerimeterTo() >= s.computePerimeter());
////        predicatesMap.put("createdAtFrom", s -> filterRequest.getCreatedAtFrom() == null || filterRequest.getCreatedAtFrom().isBefore(s.getCreatedAt()));
////        predicatesMap.put("createdAtTo", s -> filterRequest.getCreatedAtTo() == null || filterRequest.getCreatedAtTo().isAfter(s.getCreatedAt()));
////        predicatesMap.put("version", s -> filterRequest.getVersion() == null || filterRequest.getVersion().equals(s.getVersion()));
////        predicatesMap.put("sideFrom", s -> !(s instanceof Rectangle) || filterRequest.getSideFrom() == null || filterRequest.getSideFrom() <= ((Square) s).getSide());
////        predicatesMap.put("sideTo", s -> !(s instanceof Rectangle) || filterRequest.getSideTo() == null || filterRequest.getSideTo() >= ((Square) s).getSide());
////        predicatesMap.put("widthFrom", s -> !(s instanceof Rectangle) || filterRequest.getWidthFrom() == null || filterRequest.getWidthFrom() <= ((Rectangle) s).getWidth());
////        predicatesMap.put("widthTo", s -> !(s instanceof Rectangle) || filterRequest.getWidthTo() == null || filterRequest.getWidthTo() >= ((Rectangle) s).getWidth());
////        predicatesMap.put("heightFrom", s -> !(s instanceof Rectangle) || filterRequest.getHeightFrom() == null || filterRequest.getHeightFrom() <= ((Rectangle) s).getHeight());
////        predicatesMap.put("heightTo", s -> !(s instanceof Rectangle) || filterRequest.getHeightTo() == null || filterRequest.getHeightTo() >= ((Rectangle) s).getHeight());
////        predicatesMap.put("radiusFrom", s -> !(s instanceof Circle) || filterRequest.getRadiusFrom() == null || filterRequest.getRadiusFrom() <= ((Circle) s).getRadius());
////        predicatesMap.put("radiusTo", s -> !(s instanceof Circle) || filterRequest.getRadiusTo() == null || filterRequest.getRadiusTo() >= ((Circle) s).getRadius());
////
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
//    }
//
//        // Combine all predicates using AND operation
//    query.where(cb.and(predicates.toArray(new Predicate[0])));
//
//    TypedQuery<Shape> typedQuery = entityManager.createQuery(query);
//    return typedQuery.getResultList();
////        return null;
//    }
////        // Loop through the filters provided in the request and add corresponding predicates to the list
////        if (filterRequest.getFilters() != null) {
////            for (Map.Entry<String, Object> entry : filterRequest.getFilters().entrySet()) {
////                String attributeName = entry.getKey();
////                Predicate<Shape> predicate = predicatesMap.get(attributeName);
////                if (predicate != null) {
////                    predicates.add(predicate.and(predicate.test((Shape) entry.getValue())));
////                }
////            }
////        }
////
////        // Combine all predicates using AND operation
////        query.where(cb.and(predicates.toArray(new Predicate[0])));
////
////        TypedQuery<Shape> typedQuery = entityManager.createQuery(query);
////        return typedQuery.getResultList();
////    }
//
//
//    public List<Shape> filter(FilterRequest filterRequest) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> query = cb.createQuery(Shape.class);
//        Root<Shape> root = query.from(Shape.class);
//        List<Predicate> predicates = new ArrayList<>();
//
//        Map<String, Predicate<Shape>> predicatesMap = new HashMap<>();
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
//        for (Map.Entry<String, Predicate<Shape>> entry : predicatesMap.entrySet()) {
//            String filterName = entry.getKey();
//            Predicate<Shape> predicate = entry.getValue();
//            Object filterValue = filterRequest.getFilters().get(filterName);
//            if (filterValue != null) {
//                predicates.add(predicate.test((Shape) filterValue));
//            }
//        }
//
//        if (predicates.size() == 0) {
//            query.select(root);
//        } else {
//            Predicate finalPredicate = cb.and(predicates.toArray(new Predicate[predicates.size()]));
//            query.select(root).where(finalPredicate);
//        }
//
//        return entityManager.createQuery(query).getResultList();
//
////    public List<ShapeDto> filterShapes(FilterRequest filterRequest) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> query = cb.createQuery(Shape.class);
//        Root<Shape> shapeRoot = query.from(Shape.class);
//        Join<Shape, ShapeView> shapeViewJoin = shapeRoot.join("shapeView");
//
//        Map<Class<?>, TriFunction<String, Object, Object, Predicate>> filterMap = new HashMap<>();
//        filterMap.put(Double.class, (field, from, to) -> {
//            if (from != null && to != null) {
//                return cb.between(shapeRoot.get(field), from, to);
//            } else if (from != null) {
//                return cb.greaterThanOrEqualTo(shapeRoot.get(field), from);
//            } else if (to != null) {
//                return cb.lessThanOrEqualTo(shapeRoot.get(field), to);
//            } else {
//                return cb.equal(shapeRoot.get(field), from);
//            }
//        });
//        filterMap.put(String.class, (field, value, ignore) -> cb.equal(shapeRoot.get(field), value));
//        filterMap.put(LocalDateTime.class, (field, from, to) -> {
//            if (from != null && to != null) {
//                return cb.between(shapeRoot.get(field), from, to);
//            } else if (from != null) {
//                return cb.greaterThanOrEqualTo(shapeRoot.get(field), from);
//            } else if (to != null) {
//                return cb.lessThanOrEqualTo(shapeRoot.get(field), to);
//            } else {
//                return cb.equal(shapeRoot.get(field), from);
//            }
//        });
//        filterMap.put(Long.class, (field, value, ignore) -> cb.equal(shapeRoot.get(field), value));
//
//        List<Predicate> predicatesMap = new ArrayList<>();
//        predicatesMap.add((Predicate) cb.between(shapeViewJoin.get("area"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
//        predicatesMap.add((Predicate) cb.between(shapeViewJoin.get("perimeter"), filterRequest.getPerimeterFrom(), filterRequest.getPerimeterTo()));
//
//        for (Field field : Shape.class.getDeclaredFields()) {
//            try {
//                Object value = filterRequest.getClass().getMethod("get" + StringUtils.capitalize(field.getName())).invoke(filterRequest);
//                if (value != null) {
//                    Class<?> type = field.getType();
//                    Object from = filterRequest.getClass().getMethod("get" + StringUtils.capitalize(field.getName()) + "From").invoke(filterRequest);
//                    Object to = filterRequest.getClass().getMethod("get" + StringUtils.capitalize(field.getName()) + "To").invoke(filterRequest);
//                    if (filterMap.containsKey(type)) {
//                        Predicate predicate = filterMap.get(type).apply(field.getName(), from, to);
//                        predicatesMap.add(predicate);
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        query.select(shapeRoot).distinct(true).where(predicatesMap.toArray(new Predicate[]{}));
//
//        List<Shape> shapes = entityManager.createQuery(query).getResultList();
//
//        return shapes.stream()
//                .map(shape -> new ShapeDto(shape.getId(), shape.getType(), shape.getVersion(), shape.getCreatedBy(), shape.getCreatedAt().toString(),
//                        shape.getLastModifiedAt().toString(), shape.getLastModifiedBy(), shape.computeArea(), shape.computePerimeter()))
//                .collect(Collectors.toList());
//    }

//    public List<ShapeDto> filterShapes(FilterRequest filterRequest) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> query = cb.createQuery(Shape.class);
//        Root<Shape> shapeRoot = query.from(Shape.class);
//        Join<Shape, ShapeView> shapeViewJoin = shapeRoot.join("shapeView");
//
//        Map<Class<?>, Function<Field, Predicate>> typeToPredicateMap = new HashMap<>();
//        typeToPredicateMap.put(Double.class, field -> {
//            String fieldName = field.getName();
//            Double from = (Double) getFieldValue(filterRequest, fieldName + "From");
//            Double to = (Double) getFieldValue(filterRequest, fieldName + "To");
//            if (from != null && to != null) {
//                return cb.between(shapeRoot.get(fieldName), from, to);
//            } else if (from != null) {
//                return cb.greaterThanOrEqualTo(shapeRoot.get(fieldName), from);
//            } else if (to != null) {
//                return cb.lessThanOrEqualTo(shapeRoot.get(fieldName), to);
//            } else {
//                return cb.equal(shapeRoot.get(fieldName), getFieldValue(filterRequest, fieldName));
//            }
//        });
//        typeToPredicateMap.put(String.class, field -> {
//            String fieldName = field.getName();
//            return cb.equal(shapeRoot.get(fieldName), getFieldValue(filterRequest, fieldName));
//        });
//        typeToPredicateMap.put(LocalDateTime.class, field -> {
//            String fieldName = field.getName();
//            LocalDateTime from = (LocalDateTime) getFieldValue(filterRequest, fieldName + "From");
//            LocalDateTime to = (LocalDateTime) getFieldValue(filterRequest, fieldName + "To");
//            if (from != null && to != null) {
//                return cb.between(shapeRoot.get(fieldName), from, to);
//            } else if (from != null) {
//                return cb.greaterThanOrEqualTo(shapeRoot.get(fieldName), from);
//            } else if (to != null) {
//                return cb.lessThanOrEqualTo(shapeRoot.get(fieldName), to);
//            } else {
//                return cb.equal(shapeRoot.get(fieldName), getFieldValue(filterRequest, fieldName));
//            }
//        });
//        typeToPredicateMap.put(Long.class, field -> {
//            String fieldName = field.getName();
//            return cb.equal(shapeRoot.get(fieldName), getFieldValue(filterRequest, fieldName));
//        });
//
//        List<Predicate> predicatesMap = new ArrayList<>();
//        predicatesMap.add((Predicate) cb.between(shapeViewJoin.get("area"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
//        predicatesMap.add((Predicate) cb.between(shapeViewJoin.get("perimeter"), filterRequest.getPerimeterFrom(), filterRequest.getPerimeterTo()));
//
//        for (Field field : Shape.class.getDeclaredFields()) {
//            try {
//                Object value = getFieldValue(filterRequest, field.getName());
//                if (value != null) {
//                    Function<Field, Predicate> predicateFunction = typeToPredicateMap.get(field.getType());
//                    if (predicateFunction != null) {
//                        Predicate predicate = predicateFunction.apply(field);
//                        predicatesMap.add(predicate);
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//
//        query.select(shapeRoot).distinct(true).where((javax.persistence.criteria.Predicate) predicatesMap.toArray(new Predicate[]{}));
//
//        List<Shape> shapes = entityManager.createQuery(query).getResultList();
//
//        return shapes.stream()
//                .map(shape -> new ShapeDto(shape.getId(), shape.getType(), shape.getVersion(), shape.getCreatedBy(), shape.getCreatedAt().toString(),
//                        shape.getLastModifiedAt().toString(), shape.getLastModifiedBy(), shape.computeArea(), shape.computePerimeter()))
//                .collect(Collectors.toList());
//    }

//    public List<ShapeDto> filterShapes(FilterRequest filterRequest) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> query = cb.createQuery(Shape.class);
//        Root<Shape> shapeRoot = query.from(Shape.class);
//        Join<Shape, ShapeView> shapeViewJoin = shapeRoot.join("shapeView");
//
//        List<Predicate> predicatesMap = new ArrayList<>();
//        predicatesMap.add((Predicate) cb.between(shapeViewJoin.get("area"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
//        predicatesMap.add((Predicate) cb.between(shapeViewJoin.get("perimeter"), filterRequest.getPerimeterFrom(), filterRequest.getPerimeterTo()));
//
//        for (Field field : Shape.class.getDeclaredFields()) {
//            try {
//                Object value = filterRequest.getClass().getMethod("get" + StringUtils.capitalize(field.getName())).invoke(filterRequest);
//                if (value != null) {
//                    if (field.getType().equals(Double.class)) {
//                        Double from = (Double) filterRequest.getClass().getMethod("get" + StringUtils.capitalize(field.getName()) + "From").invoke(filterRequest);
//                        Double to = (Double) filterRequest.getClass().getMethod("get" + StringUtils.capitalize(field.getName()) + "To").invoke(filterRequest);
//                        if (from != null && to != null) {
//                            predicatesMap.add((Predicate) cb.between(shapeRoot.get(field.getName()), from, to));
////                        } else if (from != null) {
////                            predicatesMap.add(cb.greaterThanOrEqualTo(shapeRoot.get(field.getName()), from));
////                        } else if (to != null) {
////                            predicatesMap.add(cb.lessThanOrEqualTo(shapeRoot.get(field.getName()), to));
////                        } else {
////                            predicatesMap.add(cb.equal(shapeRoot.get(field.getName()), value));
////                        }
////                    } else if (field.getType().equals(String.class)) {
////                        predicatesMap.add(cb.equal(shapeRoot.get(field.getName()), value));
////                    } else if (field.getType().equals(LocalDateTime.class)) {
////                        LocalDateTime from = (LocalDateTime) filterRequest.getClass().getMethod("get" + StringUtils.capitalize(field.getName()) + "From").invoke(filterRequest);
////                        LocalDateTime to = (LocalDateTime) filterRequest.getClass().getMethod("get" + StringUtils.capitalize(field.getName()) + "To").invoke(filterRequest);
////                        if (from != null && to != null) {
////                            predicatesMap.add(cb.between(shapeRoot.get(field.getName()), from, to));
////                        } else if (from != null) {
////                            predicatesMap.add(cb.greaterThanOrEqualTo(shapeRoot.get(field.getName()), from));
////                        } else if (to != null) {
////                            predicatesMap.add(cb.lessThanOrEqualTo(shapeRoot.get(field.getName()), to));
////                        } else {
////                            predicatesMap.add(cb.equal(shapeRoot.get(field.getName()), value));
////                        }
////                    } else if (field.getType().equals(Long.class)) {
////                        predicatesMap.add(cb.equal(shapeRoot.get(field.getName()), value));
////                    }
////                }
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
//        }
//
////        query.select(shapeRoot).distinct(true).where(predicatesMap.toArray(new Predicate[]{}));
//
//        List<Shape> shapes = entityManager.createQuery(query).getResultList();
//
//        return shapes.stream()
//                .map(shape -> new ShapeDto(shape.getId(), shape.getType(), shape.getVersion(), shape.getCreatedBy(), shape.getCreatedAt().toString(),
//                        shape.getLastModifiedAt().toString(), shape.getLastModifiedBy(), shape.computeArea(), shape.computePerimeter()))
//                .collect(Collectors.toList());
//    }
//    public List<Shape> filterShapes2(FilterRequest filterRequest) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> query = cb.createQuery(Shape.class);
//        Root<Shape> shapeRoot = query.from(Shape.class);
//        Join<Shape, ShapeView> shapeViewJoin = shapeRoot.join("shapeView");
//
//        List<Predicate> predicatesMap = new ArrayList<>();
//        predicatesMap.add((Predicate) cb.between(shapeViewJoin.get("area"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
//        predicatesMap.add((Predicate) cb.between(shapeViewJoin.get("perimeter"), filterRequest.getPerimeterFrom(), filterRequest.getPerimeterTo()));
//
//        Map<String, Predicate<Shape>>predicatesMap = new HashMap<>();
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
//        Predicate<Shape> filterPredicate = s -> true;
//        for (Map.Entry<String, Predicate<Shape>> entry : predicatesMap.entrySet()) {
//            if (filterRequest.getFilterParams().containsKey(entry.getKey())) {
//                filterPredicate = filterPredicate.and(entry.getValue());
//            }
//        }
//
//        List<Shape> filteredShapes = getAllShapes().stream()
//                .filter(filterPredicate)
//                .collect(Collectors.toList());
//    }
//    public List<Shape> filter(FilterRequest filterRequest) {
//        List<Shape> filteredShapes = new ArrayList<>();
//        Map<String, Predicate<Shape>> predicatesMap = new HashMap<>();
//        predicatesMap.put("createdBy", s -> filterRequest.getCreatedBy() == null || filterRequest.getCreatedBy().equals(s.getCreatedBy()));
//        predicatesMap.put("shapeType", s -> filterRequest.getShapeType() == null || filterRequest.getShapeType().equals(s.getType()));
//        predicatesMap.put("areaFrom", s -> filterRequest.getAreaFrom() == null || filterRequest.getAreaFrom() <= s.computeArea());
//        predicatesMap.put("areaTo", s -> filterRequest.getAreaTo() == null || filterRequest.getAreaTo() >= s.computeArea());
//        predicatesMap.put("perimeterFrom", s -> filterRequest.getPerimeterFrom() == null || filterRequest.getPerimeterFrom() <= s.computePerimeter());
//        predicatesMap.put("perimeterTo", s -> filterRequest.getPerimeterTo() == null || filterRequest.getPerimeterTo() >= s.computePerimeter());
//        predicatesMap.put("createdAtFrom", s -> filterRequest.getCreatedAtFrom() == null || filterRequest.getCreatedAtFrom().isBefore(s.getCreatedAt()));
//        predicatesMap.put("createdAtTo", s -> filterRequest.getCreatedAtTo() == null || filterRequest.getCreatedAtTo().isAfter(s.getCreatedAt()));
//        predicatesMap.put("version", s -> filterRequest.getVersion() == null || filterRequest.getVersion().equals(s.getVersion()));
//        predicatesMap.put("sideFrom", s -> !(s instanceof Rectangle) || filterRequest.getSideFrom() == null || filterRequest.getSideFrom() <= ((Rectangle) s).getSide());
//        predicatesMap.put("sideTo", s -> !(s instanceof Rectangle) || filterRequest.getSideTo() == null || filterRequest.getSideTo() >= ((Rectangle) s).getSide());
//        predicatesMap.put("widthFrom", s -> !(s instanceof Rectangle) || filterRequest.getWidthFrom() == null || filterRequest.getWidthFrom() <= ((Rectangle) s).getWidth());
//        predicatesMap.put("widthTo", s -> !(s instanceof Rectangle) || filterRequest.getWidthTo() == null || filterRequest.getWidthTo() >= ((Rectangle) s).getWidth());
//        predicatesMap.put("heightFrom", s -> !(s instanceof Rectangle) || filterRequest.getHeightFrom() == null || filterRequest.getHeightFrom() <= ((Rectangle) s).getHeight());
//        predicatesMap.put("heightTo", s -> !(s instanceof Rectangle) || filterRequest.getHeightTo() == null || filterRequest.getHeightTo() >= ((Rectangle) s).getHeight());
//        predicatesMap.put("radiusFrom", s -> !(s instanceof Circle) || filterRequest.getRadiusFrom() == null || filterRequest.getRadiusFrom() <= ((Circle) s).getRadius());
//        predicatesMap.put("radiusTo", s -> !(s instanceof Circle) || filterRequest.getRadiusTo() == null || filterRequest.getRadiusTo() >= ((Circle) s).getRadius());
//
//        Predicate<Shape> filterPredicate = s -> true;
//        for (Map.Entry<String, Predicate<Shape>> entry : predicatesMap.entrySet()) {
//            if (filterRequest.getFilterParams().containsKey(entry.getKey())) {
//                filterPredicate = filterPredicate.and(entry.getValue());
//            }
//        }
//
//        List<Shape> filteredShapes = getAllShapes().stream()
//                .filter(filterPredicate)
//                .collect(Collectors.toList());
//    }
//        for (Shape shape : shapes) {
//            boolean shouldAddShape = true;
//            for (String field : predicatesMap.keySet()) {
//                Predicate<Shape> predicate = predicatesMap.get(field);
//                boolean fieldMatches = predicate.test(shape);
//                if (!fieldMatches) {
//                    shouldAddShape = false;
//                    break;
//                }
//            }
//            if (shouldAddShape) {
//                filteredShapes.add(shape);
//            }
//        }
//        return filteredShapes;

//    public List<Shape> filterShapes3(FilterRequest filterRequest) {
//        Map<String, Predicate<Shape>> predicatesMap = new HashMap<>();
//        predicatesMap.put("createdBy", s -> filterRequest.getCreatedBy() == null || filterRequest.getCreatedBy().equals(s.getCreatedBy()));
//        predicatesMap.put("shapeType", s -> filterRequest.getShapeType() == null || filterRequest.getShapeType().equals(s.getType()));
//        predicatesMap.put("areaFrom", s -> filterRequest.getAreaFrom() == null || filterRequest.getAreaFrom() <= s.computeArea());
//        predicatesMap.put("areaTo", s -> filterRequest.getAreaTo() == null || filterRequest.getAreaTo() >= s.computeArea());
//        predicatesMap.put("perimeterFrom", s -> filterRequest.getPerimeterFrom() == null || filterRequest.getPerimeterFrom() <= s.computePerimeter());
//        predicatesMap.put("perimeterTo", s -> filterRequest.getPerimeterTo() == null || filterRequest.getPerimeterTo() >= s.computePerimeter());
//        predicatesMap.put("createdAtFrom", s -> filterRequest.getCreatedAtFrom() == null || filterRequest.getCreatedAtFrom().isBefore(s.getCreatedAt()));
//        predicatesMap.put("createdAtTo", s -> filterRequest.getCreatedAtTo() == null || filterRequest.getCreatedAtTo().isAfter(s.getCreatedAt()));
//        predicatesMap.put("version", s -> filterRequest.getVersion() == null || filterRequest.getVersion().equals(s.getVersion()));
//        predicatesMap.put("sideFrom", s -> !(s instanceof Rectangle) || filterRequest.getSideFrom() == null || filterRequest.getSideFrom() <= ((Rectangle) s).getSide());
//        predicatesMap.put("sideTo", s -> !(s instanceof Rectangle) || filterRequest.getSideTo() == null || filterRequest.getSideTo() >= ((Rectangle) s).getSide());
//        predicatesMap.put("widthFrom", s -> !(s instanceof Rectangle) || filterRequest.getWidthFrom() == null || filterRequest.getWidthFrom() <= ((Rectangle) s).getWidth());
//        predicatesMap.put("widthTo", s -> !(s instanceof Rectangle) || filterRequest.getWidthTo() == null || filterRequest.getWidthTo() >= ((Rectangle) s).getWidth());
//        predicatesMap.put("heightFrom", s -> !(s instanceof Rectangle) || filterRequest.getHeightFrom() == null || filterRequest.getHeightFrom() <= ((Rectangle) s).getHeight());
//        predicatesMap.put("heightTo", s -> !(s instanceof Rectangle) || filterRequest.getHeightTo() == null || filterRequest.getHeightTo() >= ((Rectangle) s).getHeight());
//        predicatesMap.put("radiusFrom", s -> !(s instanceof Circle) || filterRequest.getRadiusFrom() == null || filterRequest.getRadiusFrom() <= ((Circle) s).getRadius());
//        predicatesMap.put("radiusTo", s -> !(s instanceof Circle) || filterRequest.getRadiusTo() == null || filterRequest.getRadiusTo() >= ((Circle) s).getRadius());
//
//        List<Predicate<Shape>> predicateList = new ArrayList<>();
//        for (String field : predicatesMap.keySet()) {
//            if (filterRequest.hasValue(field)) {
//                predicateList.add(predicatesMap.get(field));
//            }
//        }
//
//        return shapes.stream()
//                .filter(shape -> predicateList.stream().allMatch(p -> p.test(shape)))
//                .collect(Collectors.toList());
//    }
//    public List<Shape> filterShapes2(FilterRequest filterRequest) {
//        List<Shape> filteredShapes = new ArrayList<>();
//        for (Shape shape : shapes) {
//            boolean shouldAddShape = true;
//            Map<String, Function<Shape, Boolean>> filters = new HashMap<>();
//            filters.put("createdBy", s -> filterRequest.getCreatedBy() == null || filterRequest.getCreatedBy().equals(s.getCreatedBy()));
//            filters.put("shapeType", s -> filterRequest.getShapeType() == null || filterRequest.getShapeType().equals(s.getType()));
//            filters.put("areaFrom", s -> filterRequest.getAreaFrom() == null || filterRequest.getAreaFrom() <= s.computeArea());
//            filters.put("areaTo", s -> filterRequest.getAreaTo() == null || filterRequest.getAreaTo() >= s.computeArea());
//            filters.put("perimeterFrom", s -> filterRequest.getPerimeterFrom() == null || filterRequest.getPerimeterFrom() <= s.computePerimeter());
//            filters.put("perimeterTo", s -> filterRequest.getPerimeterTo() == null || filterRequest.getPerimeterTo() >= s.computePerimeter());
//            filters.put("createdAtFrom", s -> filterRequest.getCreatedAtFrom() == null || filterRequest.getCreatedAtFrom().isBefore(s.getCreatedAt()));
//            filters.put("createdAtTo", s -> filterRequest.getCreatedAtTo() == null || filterRequest.getCreatedAtTo().isAfter(s.getCreatedAt()));
//            filters.put("version", s -> filterRequest.getVersion() == null || filterRequest.getVersion().equals(s.getVersion()));
//            filters.put("sideFrom", s -> !(s instanceof Rectangle) || filterRequest.getSideFrom() == null || filterRequest.getSideFrom() <= ((Square) s).getSide());
//            filters.put("sideTo", s -> !(s instanceof Rectangle) || filterRequest.getSideTo() == null || filterRequest.getSideTo() >= ((Square) s).getSide());
//            filters.put("widthFrom", s -> !(s instanceof Rectangle) || filterRequest.getWidthFrom() == null || filterRequest.getWidthFrom() <= ((Rectangle) s).getWidth());
//            filters.put("widthTo", s -> !(s instanceof Rectangle) || filterRequest.getWidthTo() == null || filterRequest.getWidthTo() >= ((Rectangle) s).getWidth());
//            filters.put("heightFrom", s -> !(s instanceof Rectangle) || filterRequest.getHeightFrom() == null || filterRequest.getHeightFrom() <= ((Rectangle) s).getHeight());
//            filters.put("heightTo", s -> !(s instanceof Rectangle) || filterRequest.getHeightTo() == null || filterRequest.getHeightTo() >= ((Rectangle) s).getHeight());
//            filters.put("radiusFrom", s -> !(s instanceof Circle) || filterRequest.getRadiusFrom() == null || filterRequest.getRadiusFrom() <= ((Circle) s).getRadius());
//            filters.put("radiusTo", s -> !(s instanceof Circle) || filterRequest.getRadiusTo() == null || filterRequest.getRadiusTo() >= ((Circle) s).getRadius());

//            for (String field : filters.keySet()) {
//                Function<Shape, Boolean> filter = filters.get(field);
//                boolean fieldMatches = filter.apply(shape);
//                if (!fieldMatches) {
//                    shouldAddShape = false;
//                    break;
//                }
//            }
//            if (shouldAddShape) {
//                filteredShapes.add(shape);
//            }
//        }
//        return filteredShapes;
//    }

//public List<ShapeDto> filterShapes(FilterRequest filterRequest) {
//    CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//    CriteriaQuery<Shape> query = cb.createQuery(Shape.class);
//    Root<Shape> shapeRoot = query.from(Shape.class);
//    Join<Shape, ShapeView> shapeViewJoin = shapeRoot.join("shapeView");
//
//    List<Predicate> predicatesMap = new ArrayList<>();
//
//    for (Field field : FilterRequest.class.getDeclaredFields()) {
//        try {
//            Object value = field.get(filterRequest);
//            if (value != null) {
//                switch (field.getName()) {
//                    case "createdBy":
//                        predicatesMap.add(cb.equal(shapeRoot.get("createdBy"), value));
//                        break;
//                    case "shapeType":
//                        predicatesMap.add(cb.equal(shapeRoot.get("type"), value));
//                        break;
//                    case "areaFrom":
//                        predicatesMap.add(cb.greaterThanOrEqualTo(shapeViewJoin.get("area"), (Double) value));
//                        break;
//                    case "areaTo":
//                        predicatesMap.add(cb.lessThanOrEqualTo(shapeViewJoin.get("area"), (Double) value));
//                        break;
//                    case "perimeterFrom":
//                        predicatesMap.add(cb.greaterThanOrEqualTo(shapeViewJoin.get("perimeter"), (Double) value));
//                        break;
//                    case "perimeterTo":
//                        predicatesMap.add(cb.lessThanOrEqualTo(shapeViewJoin.get("perimeter"), (Double) value));
//                        break;
//                    case "createdAtFrom":
//                        predicatesMap.add(cb.greaterThanOrEqualTo(shapeRoot.get("createdAt"), (LocalDateTime) value));
//                        break;
//                    case "createdAtTo":
//                        predicatesMap.add(cb.lessThanOrEqualTo(shapeRoot.get("createdAt"), (LocalDateTime) value));
//                        break;
//                    case "version":
//                        predicatesMap.add(cb.equal(shapeRoot.get("version"), value));
//                        break;
//                    case "sideFrom":
//                        predicatesMap.add(cb.greaterThanOrEqualTo(shapeViewJoin.get("side"), (Double) value));
//                        break;
//                    case "sideTo":
//                        predicatesMap.add(cb.lessThanOrEqualTo(shapeViewJoin.get("side"), (Double) value));
//                        break;
//                    case "widthFrom":
//                        predicatesMap.add(cb.greaterThanOrEqualTo(shapeViewJoin.get("width"), (Double) value));
//                        break;
//                    case "widthTo":
//                        predicatesMap.add(cb.lessThanOrEqualTo(shapeViewJoin.get("width"), (Double) value));
//                        break;
//                    case "heightFrom":
//                        predicatesMap.add(cb.greaterThanOrEqualTo(shapeViewJoin.get("height"), (Double) value));
//                        break;
//                    case "heightTo":
//                        predicatesMap.add(cb.lessThanOrEqualTo(shapeViewJoin.get("height"), (Double) value));
//                        break;
//                    case "radiusFrom":
//                        predicatesMap.add(cb.greaterThanOrEqualTo(shapeViewJoin.get("radius"), (Double) value));
//                        break;
//                    case "radiusTo":
//                        predicatesMap.add(cb.lessThanOrEqualTo(shapeViewJoin.get("radius"), (Double) value));
//                        break;
//                }
//            }
//        } catch (IllegalAccessException e) {
//            // handle exception
//        }
//    }
//
//    query.select(shapeRoot)
//         .where(predicatesMap.toArray(new Predicate[0]));
//
//    List<Shape> shapes = entityManager.createQuery(query).getResultList();
//
//    return shapes.stream()
//            .map(shape -> new ShapeDto(shape.getId(), shape.getType(), shape.getVersion(), shape.getCreatedBy(), shape.getCreatedAt().toString(),
//                    shape.getLastModifiedAt().toString(), shape.getLastModifiedBy(), shape.computeArea(), shape.computePerimeter()))
//            .collect(Collectors.toList());
//}

        //List<Predicate> predicatesMap = new ArrayList<>();
        //for (Field field : Shape.class.getDeclaredFields()) {
        //    String fieldName = field.getName();
        //    Object value = filterRequest.getClass().getMethod("get" + StringUtils.capitalize(fieldName)).invoke(filterRequest);
        //    if (value != null) {
        //        Path<Object> path = shapeRoot.get(fieldName);
        //        if (field.getType().equals(Double.class)) {
        //            Double from = (Double) filterRequest.getClass().getMethod("get" + StringUtils.capitalize(fieldName) + "From").invoke(filterRequest);
        //            Double to = (Double) filterRequest.getClass().getMethod("get" + StringUtils.capitalize(fieldName) + "To").invoke(filterRequest);
        //            if (from != null && to != null) {
        //                predicatesMap.add(cb.between(path, from, to));
        //            } else if (from != null) {
        //                predicatesMap.add(cb.greaterThanOrEqualTo(path, from));
        //            } else if (to != null) {
        //                predicatesMap.add(cb.lessThanOrEqualTo(path, to));
        //            } else {
        //                predicatesMap.add(cb.equal(path, value));
        //            }
        //        } else if (field.getType().equals(String.class) || field.getType().equals(LocalDateTime.class) || field.getType().equals(Long.class)) {
        //            predicatesMap.add(cb.equal(path, value));
        //        }
        //    }
        //}


//String hql = "select new com.example.shapesecurity.model.dto.ShapeDto(s.id, s.type, s.version, s.createdBy, s.createdAt.toString(), s.lastModifiedAt.toString(), s.lastModifiedBy, s.computeArea(), s.computePerimeter()) " +
//        "from Shape s join s.shapeView sv " +
//        "where sv.area between :areaFrom and :areaTo " +
//        "and sv.perimeter between :perimeterFrom and :perimeterTo ";
//for (Field field : Shape.class.getDeclaredFields()) {
//    String fieldName = field.getName();
//    String getter = "get" + StringUtils.capitalize(fieldName);
//    Object value = filterRequest.getClass().getMethod(getter).invoke(filterRequest);
//    if (value != null) {
//        String paramFrom = fieldName + "From";
//        String paramTo = fieldName + "To";
//        if (field.getType().equals(Double.class)) {
//            Double from = (Double) filterRequest.getClass().getMethod(paramFrom).invoke(filterRequest);
//            Double to = (Double) filterRequest.getClass().getMethod(paramTo).invoke(filterRequest);
//            if (from != null && to != null) {
//                hql += "and s." + fieldName + " between :" + paramFrom + " and :" + paramTo + " ";
//            } else if (from != null) {
//                hql += "and s." + fieldName + " >= :" + paramFrom + " ";
//            } else if (to != null) {
//                hql += "and s." + fieldName + " <= :" + paramTo + " ";
//            } else {
//                hql += "and s." + fieldName + " = :" + fieldName + " ";
//            }
//        } else if (field.getType().equals(String.class) || field.getType().equals(LocalDateTime.class) || field.getType().equals


//    public List<ShapeDto> filterShapes(FilterRequest filterRequest) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> query = cb.createQuery(Shape.class);
//        Root<Shape> shapeRoot = query.from(Shape.class);
//        Join<Shape, ShapeView> shapeViewJoin = shapeRoot.join("shapeView");
//
//        List<Predicate> predicatesMap = new ArrayList<>();
//        predicatesMap.add(cb.between(shapeViewJoin.get("area"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
//        predicatesMap.add(cb.between(shapeViewJoin.get("perimeter"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
//
//        query.select(shapeRoot).
//
//                where(predicatesMap.toArray(new Predicate[]{
//                }));
//
//        List<Shape> shapes = entityManager.createQuery(query).getResultList();
//
//        return shapes.stream()
//                .map(shape -> new ShapeDto(shape.getId(), shape.getType(), shape.getVersion(), shape.getCreatedBy(), shape.getCreatedAt().toString(),
//                        shape.getLastModifiedAt().toString(), shape.getLastModifiedBy(), shape.computeArea(), shape.computePerimeter()))
//                .collect(Collectors.toList());
//    }


//    public List<Shape> filterShapes(FilterRequest filterRequest) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> cq = cb.createQuery(Shape.class);
//        Root<Shape> shapeRoot = cq.from(Shape.class);
//        Join<Shape, ShapeView> shapeViewJoin = shapeRoot.join("shapeView");
//
//// tworzymy listę predykatów
//        List<Predicate> predicatesMap = new ArrayList<>();
//
//// filtrujemy ShapeView na podstawie kryteriów
//        predicatesMap.add(cb.between(shapeViewJoin.get("area"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
//        predicatesMap.add(cb.between(shapeViewJoin.get("perimeter"), filterRequest.getPerimeterFrom(), filterRequest.getPerimeterTo()));
//
//// łączymy Shape i ShapeView za pomocą predykatów
//        Predicate predicate = cb.and(predicatesMap.toArray(new Predicate[predicatesMap.size()]));
//        cq.where(predicate);
//
//// wykonujemy zapytanie i zwracamy wyniki
//        TypedQuery<Shape> query = entityManager.createQuery(cq);
//        List<Shape> result = query.getResultList();
//        return result;
//    }
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> cq = cb.createQuery(Shape.class);
//        Root<Shape> shapeRoot = cq.from(Shape.class);
//
//        List<Predicate> predicatesMap = new ArrayList<>();
//
//        Map<String, Object> filterMap = new HashMap<>();
//        filterMap.put("createdBy", filterRequest.getCreatedBy());
//        filterMap.put("type", filterRequest.getShapeType());
//        filterMap.put("area", Arrays.asList(filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
//        filterMap.put("perimeter", Arrays.asList(filterRequest.getPerimeterFrom(), filterRequest.getPerimeterTo()));
//        filterMap.put("createdAt", Arrays.asList(filterRequest.getCreatedAtFrom(), filterRequest.getCreatedAtTo()));
//        filterMap.put("version", filterRequest.getVersion());
//
////        if (Rectangle.class.getSimpleName().equalsIgnoreCase(filterRequest.getShapeType())) {
//            filterMap.put("width", Arrays.asList(filterRequest.getWidthFrom(), filterRequest.getWidthTo()));
//            filterMap.put("height", Arrays.asList(filterRequest.getHeightFrom(), filterRequest.getHeightTo()));
////        } else if (Square.class.getSimpleName().equalsIgnoreCase(filterRequest.getShapeType())) {
//            filterMap.put("side", Arrays.asList(filterRequest.getSideFrom(), filterRequest.getSideTo()));
////        }
//
//        for (Map.Entry<String, Object> entry : filterMap.entrySet()) {
//            String fieldName = entry.getKey();
//            Object fieldValue = entry.getValue();
//
//            if (fieldValue == null) {
//                continue;
//            }
//
//            Join<Shape, ShapeView> shapeViewJoin = shapeRoot.join("shape");

//            if (fieldName.equals("type")) {
//                predicatesMap.add(cb.equal(shapeRoot.get(fieldName), fieldValue));
//            } else if (fieldName.equals("version")) {
//                predicatesMap.add(cb.equal(shapeRoot.get(fieldName), fieldValue));
//            } else if (fieldName.equals("createdAt")) {
//                List<LocalDateTime> createdAtList = (List<LocalDateTime>) fieldValue;
//                predicatesMap.add(cb.between(shapeRoot.get(fieldName), createdAtList.get(0), createdAtList.get(1)));
//            } else if (fieldName.equals("width") || fieldName.equals("height") || fieldName.equals("side")) {
//                Join<Shape, ? extends Shape> shapeJoin = shapeRoot.join(fieldName.toLowerCase());
//                List<Double> sizeList = (List<Double>) fieldValue;
//                predicatesMap.add(cb.between(shapeJoin.get("size"), sizeList.get(0), sizeList.get(1)));
//            } else {
//                predicatesMap.add(cb.equal(shapeViewJoin.get(fieldName), fieldValue));
//            }
//}

//        cq.where(predicatesMap.toArray(new Predicate[0]));
//        TypedQuery<Shape> query = entityManager.createQuery(cq);
//        return query.getResultList();
//    }
//    public List<Shape> filterShapes(FilterRequest filterRequest) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> cq = cb.createQuery(Shape.class);
//        Root<Shape> shapeRoot = cq.from(Shape.class);
//        Join<Shape, ShapeView> shapeViewJoin = shapeRoot.join("shape", JoinType.LEFT);
////        Join<Shape, Rectangle> rectangleJoin = shapeRoot.join("rectangle", JoinType.LEFT);
////        Join<Shape, Square> squareJoin = shapeRoot.join("square", JoinType.LEFT);
//
//        List<Predicate> predicatesMap = new ArrayList<>();
//
//        // filter by createdBy
//        String createdBy = filterRequest.getCreatedBy();
//        if (createdBy != null) {
//            predicatesMap.add(cb.equal(shapeRoot.get("createdBy"), createdBy));
//        }
//
//        // filter by shapeType
//        String shapeType = filterRequest.getShapeType();
//        if (shapeType != null) {
//            predicatesMap.add(cb.equal(shapeRoot.get("type"), shapeType));
//        }
//
//        // filter by area range
//        Double areaFrom = filterRequest.getAreaFrom();
//        Double areaTo = filterRequest.getAreaTo();
//        if (areaFrom != null && areaTo != null) {
//            predicatesMap.add(cb.between(shapeViewJoin.get("area"), areaFrom, areaTo));
//        }
//
//        // filter by perimeter range
//        Double perimeterFrom = filterRequest.getPerimeterFrom();
//        Double perimeterTo = filterRequest.getPerimeterTo();
//        if (perimeterFrom != null && perimeterTo != null) {
//            predicatesMap.add(cb.between(shapeViewJoin.get("perimeter"), perimeterFrom, perimeterTo));
//        }
//
//        // filter by createdAt range
//        LocalDateTime createdAtFrom = filterRequest.getCreatedAtFrom();
//        LocalDateTime createdAtTo = filterRequest.getCreatedAtTo();
//        if (createdAtFrom != null && createdAtTo != null) {
//            predicatesMap.add(cb.between(shapeRoot.get("createdAt"), createdAtFrom, createdAtTo));
//        }
//
//        // filter by version
//        Long version = filterRequest.getVersion();
//        if (version != null) {
//            predicatesMap.add(cb.equal(shapeRoot.get("version"), version));
//        }
//
//        // filter by shape properties
////        if (Rectangle.class.getSimpleName().equalsIgnoreCase(shapeType)) {
//        Double widthFrom = filterRequest.getWidthFrom();
//        Double widthTo = filterRequest.getWidthTo();
//        if (widthFrom != null && widthTo != null) {
////            predicatesMap.add(cb.between(rectangleJoin.get("width"), widthFrom, widthTo));
////            }
//
//            Double heightFrom = filterRequest.getHeightFrom();
//            Double heightTo = filterRequest.getHeightTo();
//            if (heightFrom != null && heightTo != null) {
////                predicatesMap.add(cb.between(rectangleJoin.get("height"), heightFrom, heightTo));
//            }
////        } else if (Square.class.getSimpleName().equalsIgnoreCase(shapeType)) {
//            Double sideFrom = filterRequest.getSideFrom();
//            Double sideTo = filterRequest.getSideTo();
////            if (sideFrom != null && sideTo != null) {
////                predicatesMap.add(cb.between(squareJoin.get("side"), sideFrom, sideTo));
////            }
//            }
//
//            cq.where(predicatesMap.toArray(new Predicate[0]));
//            TypedQuery<Shape> query = entityManager.createQuery(cq);
//            return query.getResultList();
//        }
//    }


//    public List<Shape> filterShapes(FilterRequest filterRequest) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> cq = cb.createQuery(Shape.class);
//        Root<Shape> shapeRoot = cq.from(Shape.class);
//
//        List<Predicate> predicatesMap = new ArrayList<>();
//
//        Map<String, Object> filterMap = new HashMap<>();
//        filterMap.put("createdBy", filterRequest.getCreatedBy());
//        filterMap.put("type", filterRequest.getShapeType());
////        filterMap.put("area", Arrays.asList(filterRequest.getAreaFrom(), filterRequest.getAreaTo()));
////        filterMap.put("perimeter", Arrays.asList(filterRequest.getPerimeterFrom(), filterRequest.getPerimeterTo()));
//        filterMap.put("createdAt", Arrays.asList(filterRequest.getCreatedAtFrom(), filterRequest.getCreatedAtTo()));
//        filterMap.put("version", filterRequest.getVersion());
//
//        filterMap.put("width", Arrays.asList(filterRequest.getWidthFrom(), filterRequest.getWidthTo()));
//        filterMap.put("height", Arrays.asList(filterRequest.getHeightFrom(), filterRequest.getHeightTo()));
//        filterMap.put("side", Arrays.asList(filterRequest.getSideFrom(), filterRequest.getSideTo()));
//
//        for (Map.Entry<String, Object> entry : filterMap.entrySet()) {
//            String fieldName = entry.getKey();
//            Object fieldValue = entry.getValue();
//
//            if (fieldValue == null) {
//                continue;
//            }
//
//            Join<Shape, ShapeView> shapeViewJoin = shapeRoot.join("shapeView");
//
//            if (fieldName.equals("areaFrom")) {
//                predicatesMap.add(cb.equal(shapeRoot.get(fieldName), fieldValue));
//            } else if (fieldName.equals("version")) {
//                predicatesMap.add(cb.equal(shapeRoot.get(fieldName), fieldValue));
//            } else if (fieldName.equals("createdAt")) {
//                List<LocalDateTime> createdAtList = (List<LocalDateTime>) fieldValue;
//                predicatesMap.add(cb.between(shapeRoot.get(fieldName), createdAtList.get(0), createdAtList.get(1)));
//            } else if (fieldName.equals("width") || fieldName.equals("height") || fieldName.equals("side")) {
//                Join<Shape, ? extends Shape> shapeJoin = shapeRoot.join(fieldName.toLowerCase());
//                List<Double> sizeList = (List<Double>) fieldValue;
//                predicatesMap.add(cb.between(shapeJoin.get("size"), sizeList.get(0), sizeList.get(1)));
//            } else {
//                predicatesMap.add(cb.equal(shapeViewJoin.get(fieldName), fieldValue));
//            }
//        }
//
//        cq.where(predicatesMap.toArray(new Predicate[0]));
//        TypedQuery<Shape> query = entityManager.createQuery(cq);
//        return query.getResultList();
//    }


//    public List<ShapeView> filterShapes(FilterRequest filterRequest) {
//    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//    CriteriaQuery<ShapeView> criteriaQuery = criteriaBuilder.createQuery(ShapeView.class);
//    Root<Shape> root = criteriaQuery.from(Shape.class);
//
//    List<Predicate> predicatesMap = new ArrayList<>();
//
//    // Dodajemy ograniczenia na createdBy i shapeType
//    if (filterRequest.getCreatedBy() != null) {
//        predicatesMap.add(criteriaBuilder.equal(root.get("createdBy"), filterRequest.getCreatedBy()));
//    }
//    if (filterRequest.getShapeType() != null) {
//        predicatesMap.add(criteriaBuilder.equal(root.get("type"), filterRequest.getShapeType()));
//    }
//
//    // Dodajemy ograniczenia na area i perimeter
//    Expression<Double> areaExpression = criteriaBuilder.selectCase()
//            .when(criteriaBuilder.between(root.get("area"), filterRequest.getAreaFrom(), filterRequest.getAreaTo()),
//                    root.get("area"))
//            .otherwise(null);
//    Expression<Double> perimeterExpression = criteriaBuilder.selectCase()
//            .when(criteriaBuilder.between(root.get("perimeter"), filterRequest.getPerimeterFrom(), filterRequest.getPerimeterTo()),
//                    root.get("perimeter"))
//            .otherwise(null);
//    criteriaQuery.select(criteriaBuilder.construct(ShapeView.class, areaExpression, perimeterExpression));
//
//    // Dodajemy ograniczenia na createdAt i version
//    if (filterRequest.getCreatedAtFrom() != null) {
//        predicatesMap.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), filterRequest.getCreatedAtFrom()));
//    }
//    if (filterRequest.getCreatedAtTo() != null) {
//        predicatesMap.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), filterRequest.getCreatedAtTo()));
//    }
//    if (filterRequest.getVersion() != null) {
//        predicatesMap.add(criteriaBuilder.equal(root.get("version"), filterRequest.getVersion()));
//    }
//
//    // Dodajemy ograniczenia na boki, szerokość, wysokość i promień
//    if (filterRequest.getSideFrom() != null || filterRequest.getSideTo() != null) {
//        predicatesMap.add(criteriaBuilder.or(
//                criteriaBuilder.between(root.get("side"), filterRequest.getSideFrom(), filterRequest.getSideTo()),
//                criteriaBuilder.isNull(root.get("side"))));
//    }
//    if (filterRequest.getWidthFrom() != null || filterRequest.getWidthTo() != null) {
//        predicatesMap.add(criteriaBuilder.or(
//                criteriaBuilder.between(root.get("width"), filterRequest.getWidthFrom(), filterRequest.getWidthTo()),
//                criteriaBuilder.isNull(root.get("width"))));
//    }
//    if (filterRequest.getHeightFrom() != null || filterRequest.getHeightTo() != null) {
//        predicatesMap.add(criteriaBuilder.or(
//                criteriaBuilder.between(root.get("height"), filterRequest.getHeightFrom(), filterRequest.getHeightTo()),
//                criteriaBuilder.isNull(root.get("height"))
//}


//abstract class ShapeFilterStrategy {
//    public abstract Predicate applyCriteria(CriteriaBuilder cb, Root<Shape> root, Object value);
//}
//
//class CreatedByFilterStrategy extends ShapeFilterStrategy {
//    @Override
//    public Predicate applyCriteria(CriteriaBuilder cb, Root<Shape> root, Object value) {
//        return cb.equal(root.get("createdBy"), value);
//    }
//}
//
//class TypeFilterStrategy extends ShapeFilterStrategy {
//    @Override
//    public Predicate applyCriteria(CriteriaBuilder cb, Root<Shape> root, Object value) {
//        return cb.equal(root.get("type"), value);
//    }
//}
//
//// dodać implementacje dla pozostałych przypadków
//
//public List<ShapeView> filterShapes(FilterRequest filterRequest) {
//    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//    CriteriaQuery<ShapeView> criteriaQuery = criteriaBuilder.createQuery(ShapeView.class);
//    Root<Shape> root = criteriaQuery.from(Shape.class);
//
//    List<Predicate> predicatesMap = filterRequest.getMap().entrySet().stream()
//            .filter(entry -> entry.getValue() != null)
//            .flatMap(entry -> {
//                String key = entry.getKey();
//                Object value = entry.getValue();
//                ShapeFilterStrategy filterStrategy;
//                switch (key) {
//                    case "createdBy":
//                        filterStrategy = new CreatedByFilterStrategy();
//                        break;
//                    case "type":
//                        filterStrategy = new TypeFilterStrategy();
//                        break;
//                    // dodaj implementacje dla pozostałych przypadków
//                    default:
//                        return Stream.empty();
//                }
//                return Stream.of(filterStrategy.applyCriteria(criteriaBuilder, root, value));
//            })
//            .collect(Collectors.toList());
//
//    criteriaQuery.where(predicatesMap.toArray(new Predicate[0]));
//    Expression<Double> areaExpression = criteriaBuilder.selectCase()
//    // reszta metody pozostaje bez zmian
//}
//public List<Shape> filterShapes(FilterRequest filterRequest) {
//    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//    CriteriaQuery<Shape> criteriaQuery = criteriaBuilder.createQuery(Shape.class);
//    Root<Shape> shapeRoot = criteriaQuery.from(Shape.class);
//    Join<Shape, ShapeView> shapeViewJoin = shapeRoot.join("shapeView");
//
//    List<Predicate> predicatesMap = new ArrayList<>();
//
//    if (filterRequest.getCreatedBy() != null) {
//        predicatesMap.add(criteriaBuilder.equal(shapeRoot.get("createdBy"), filterRequest.getCreatedBy()));
//    }
//    if (filterRequest.getShapeType() != null) {
//        predicatesMap.add(criteriaBuilder.equal(shapeRoot.get("type"), filterRequest.getShapeType()));
//    }
//    if (filterRequest.getAreaFrom() != null) {
//        predicatesMap.add(criteriaBuilder.greaterThanOrEqualTo(shapeViewJoin.get("area"), filterRequest.getAreaFrom()));
//    }
//    if (filterRequest.getAreaTo() != null) {
//        predicatesMap.add(criteriaBuilder.lessThanOrEqualTo(shapeViewJoin.get("area"), filterRequest.getAreaTo()));
//    }
//    if (filterRequest.getPerimeterFrom() != null) {
//        predicatesMap.add(criteriaBuilder.greaterThanOrEqualTo(shapeViewJoin.get("perimeter"), filterRequest.getPerimeterFrom()));
//    }
//    if (filterRequest.getPerimeterTo() != null) {
//        predicatesMap.add(criteriaBuilder.lessThanOrEqualTo(shapeViewJoin.get("perimeter"), filterRequest.getPerimeterTo()));
//    }
//    if (filterRequest.getVersion() != null) {
//        predicatesMap.add(criteriaBuilder.equal(shapeRoot.get("version"), filterRequest.getVersion()));
//    }
//    if (filterRequest.getCreatedAtFrom() != null) {
//        predicatesMap.add(criteriaBuilder.greaterThanOrEqualTo(shapeRoot.get("createdAt"), filterRequest.getCreatedAtFrom()));
//    }
//    if (filterRequest.getCreatedAtTo() != null) {
//        predicatesMap.add(criteriaBuilder.lessThanOrEqualTo(shapeRoot.get("createdAt"), filterRequest.getCreatedAtTo()));
//    }
//    if (filterRequest.getSideFrom() != null || filterRequest.getSideTo() != null) {
//        Subquery<Square> squareSubquery = criteriaQuery.subquery(Square.class);
//        Root<Square> squareRoot = squareSubquery.from(Square.class);
//        squareSubquery.select(squareRoot.get("id"))
//                .where(shapeRoot.get("id").in(squareRoot.get("id")))
//                .where(filterRequest.getSideFrom() != null ? criteriaBuilder.greaterThanOrEqualTo(squareRoot.get("side"), filterRequest.getSideFrom()) : null)
//                .where(filterRequest.getSideTo() != null ? criteriaBuilder.lessThanOrEqualTo(squareRoot.get("side"), filterRequest.getSideTo()) : null);
//        predicatesMap.add(criteriaBuilder.exists(squareSubquery));
//    }
//    if (filterRequest.getWidthFrom() != null || filterRequest.getWidthTo() != null || filterRequest.getHeightFrom() != null || filterRequest.getHeightTo() != null) {
//        Subquery<Rectangle> rectangleSubquery = criteriaQuery.subquery(Rectangle.class);
//        Root<Rectangle> rectangleRoot = rectangleSubquery.from(Rectangle.class);
//        rectangleSubquery.select(rectangleRoot.get("id"))
//                .where(shapeRoot.get("id").in(rectangleRoot.get("id")))
//                .where(filterRequest.getWidthFrom() != null ? criteriaBuilder.greaterThanOrEqualTo(rectangleRoot.get("width"), filterRequest.getWidthFrom()) : null)
//                .where(filterRequest.getWidthTo() != null ? criteria
