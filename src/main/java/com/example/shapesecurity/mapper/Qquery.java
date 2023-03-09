package com.example.shapesecurity.mapper;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.dto.ShapeDto;
import com.example.shapesecurity.model.shape.*;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Repository
@RequiredArgsConstructor
public class Qquery {
    private final EntityManager entityManager;

    private QCircle qCircle = QCircle.circle;
    private QSquare qSquare = QSquare.square;
    private QRectangle qRectangle = QRectangle.rectangle;
    private QShape qShape = QShape.shape;
//    public List<ShapeDto> testNowyaa(FilterRequest filterRequest) {
//
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> query = builder.createQuery(Shape.class);
//        Root<Shape> root = query.from(Shape.class);
//        List<Predicate> predicates = new ArrayList<>();
//
//        if (filterRequest.getShapeType() != null) {
//            predicates.add((Predicate) builder.equal(root.get("shapeView").get("type"), filterRequest.getShapeType()));
//        }
//        if (filterRequest.getRadiusFrom() != null) {
//            predicates.add((Predicate) builder.greaterThanOrEqualTo(root.get("shapeView").get("radius"), filterRequest.getRadiusFrom()));
//        }
//        if (filterRequest.getRadiusTo() != null) {
//            predicates.add((Predicate) builder.lessThanOrEqualTo(root.get("shapeView").get("radius"), filterRequest.getRadiusTo()));
//        }
//        if (filterRequest.getAreaFrom() != null) {
//            predicates.add((Predicate) builder.greaterThanOrEqualTo(root.get("shapeView").get("area"), filterRequest.getAreaFrom()));
//        }
//        if (filterRequest.getAreaTo() != null) {
//            predicates.add((Predicate) builder.lessThanOrEqualTo(root.get("shapeView").get("area"), filterRequest.getAreaTo()));
//        }
//        if (filterRequest.getWidthFrom() != null) {
//            predicates.add((Predicate) builder.greaterThanOrEqualTo(root.get("shapeView").get("width"), filterRequest.getWidthFrom()));
//        }
//        if (filterRequest.getWidthTo() != null) {
//            predicates.add((Predicate) builder.lessThanOrEqualTo(root.get("shapeView").get("width"), filterRequest.getWidthTo()));
//        }
//        if (filterRequest.getHeightFrom() != null) {
//            predicates.add((Predicate) builder.greaterThanOrEqualTo(root.get("shapeView").get("height"), filterRequest.getHeightFrom()));
//        }
//        if (filterRequest.getHeightTo() != null) {
//            predicates.add((Predicate) builder.lessThanOrEqualTo(root.get("shapeView").get("height"), filterRequest.getHeightTo()));
//        }
//        if (filterRequest.getSideFrom() != null) {
//            predicates.add((Predicate) builder.greaterThanOrEqualTo(root.get("shapeView").get("side"), filterRequest.getSideFrom()));
//        }
//        if (filterRequest.getSideTo() != null) {
//            predicates.add((Predicate) builder.lessThanOrEqualTo(root.get("shapeView").get("side"), filterRequest.getSideTo()));
//        }
//
//        Predicate finalPredicate = builder.and(predicates.toArray(new Predicate[predicates.size()]));
//
//        query.select(root)
//                .where(finalPredicate);
//
//        TypedQuery<Shape> typedQuery = entityManager.createQuery(query);
//        List<Shape> shapes = typedQuery.getResultList();
//
//        return shapes.stream()
//                .map(shape -> ShapeDto.builder()
//                        .id(shape.getId())
//                        .type(shape.getType())
//                        .createdBy(shape.getCreatedBy())
//                        .createdAt(shape.getCreatedAt().toString())
//                        .version(shape.getVersion())
//                        .area(shape.computeArea())
//                        .perimeter(shape.computePerimeter())
//                        .build())
//                .collect(Collectors.toList());
//    }
//
//    public List<ShapeDto> testNowy33(FilterRequest filterRequest) {
//
//        QShapeView qShapeView = QShapeView.shapeView;
//
//        List<Predicate> predicates = Stream.of(
//                        filterRequest.getShapeType() != null ? qShapeView.shape.type.eq(filterRequest.getShapeType()) : null,
//                        filterRequest.getRadiusFrom() != null ? qShapeView.radius.goe(filterRequest.getRadiusFrom()) : null,
//                        filterRequest.getRadiusTo() != null ? qShapeView.radius.loe(filterRequest.getRadiusTo()) : null,
//                        filterRequest.getAreaFrom() != null ? QShapeView.shapeView.area.goe(filterRequest.getAreaFrom()) : null,
//                        filterRequest.getAreaTo() != null ? QShapeView.shapeView.area.loe(filterRequest.getAreaTo()) : null,
//                        filterRequest.getWidthFrom() != null ? qShapeView.width.goe(filterRequest.getWidthFrom()) : null,
//                        filterRequest.getWidthTo() != null ? qShapeView.width.loe(filterRequest.getWidthTo()) : null,
//                        filterRequest.getHeightFrom() != null ? qShapeView.height.goe(filterRequest.getHeightFrom()) : null,
//                        filterRequest.getHeightTo() != null ? qShapeView.height.loe(filterRequest.getHeightTo()) : null,
//                        filterRequest.getSideTo() != null ? qShapeView.side.loe(filterRequest.getSideTo()) : null,
//                        filterRequest.getSideFrom() != null ? qShapeView.side.goe(filterRequest.getSideFrom()) : null
//                )
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());
//
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> query = builder.createQuery(Shape.class);
//        Root<Shape> root = query.from(Shape.class);
//
//        Predicate finalPredicate = builder.and(predicates.toArray(new Predicate[0]));
//
//        query.select(root)
//                .where(finalPredicate);
//
//        List<Shape> shapes = entityManager.createQuery(query).getResultList();
//
//        return shapes.stream()
//                .map(shape -> ShapeDto.builder()
//                        .id(shape.getId())
//                        .type(shape.getType())
//                        .createdBy(shape.getCreatedBy())
//                        .createdAt(shape.getCreatedAt().toString())
//                        .version(shape.getVersion())
//                        .area(shape.computeArea())
//                        .perimeter(shape.computePerimeter())
//                        .build())
//                .collect(Collectors.toList());
//    }
    public List<ShapeDto> testNowy(FilterRequest filterRequest){

        QShape qShape = QShape.shape;
        QShapeView qShapeView = QShapeView.shapeView;

        List<BooleanExpression> expressions = Stream.of(
                        filterRequest.getShapeType() != null ? qShapeView.shape.type.eq(filterRequest.getShapeType()) : null,
                        filterRequest.getRadiusFrom() != null ? qShapeView.shape.type.in("CIRCLE").and(qShapeView.radius.goe(filterRequest.getRadiusFrom())) : null,
                        filterRequest.getRadiusTo() != null ? qShapeView.shape.type.in("CIRCLE").and(qShapeView.radius.loe(filterRequest.getRadiusTo())) : null,
                        filterRequest.getAreaFrom() != null ? QShapeView.shapeView.area.goe(filterRequest.getAreaFrom()) : null,
                        filterRequest.getAreaTo() != null ? QShapeView.shapeView.area.loe(filterRequest.getAreaTo()) : null,
                        filterRequest.getWidthFrom() != null ? qShapeView.shape.type.in("RECTANGLE").and(qShapeView.width.goe(filterRequest.getWidthFrom())) : null,
                        filterRequest.getWidthTo() != null ? qShapeView.shape.type.in("RECTANGLE").and(qShapeView.width.loe(filterRequest.getWidthTo())) : null,
                        filterRequest.getHeightFrom() != null ? qShapeView.shape.type.in("RECTANGLE").and(qShapeView.height.goe(filterRequest.getHeightFrom())) : null,
                        filterRequest.getHeightTo() != null ? qShapeView.shape.type.in("RECTANGLE").and(qShapeView.height.loe(filterRequest.getHeightTo())) : null,
                        filterRequest.getSideTo() != null ? qShapeView.shape.type.in("SQUARE").and(qShapeView.side.loe(filterRequest.getSideTo())) : null,
                        filterRequest.getSideFrom() != null ? qShapeView.shape.type.in("SQUARE").and(qShapeView.side.goe(filterRequest.getSideFrom())) : null)
                .filter(Objects::nonNull)
                .toList();

        BooleanExpression finalExpression = expressions.stream()
                .reduce(BooleanExpression::and)
                .orElse(null);
       return new JPAQuery<>(entityManager)
                .select(qShape)
                .from(qShape)
                .where(finalExpression)
               .fetch().stream()
                .map(shape -> ShapeDto.builder()
                        .id(shape.getId())
                        .type(shape.getType())
                        .createdBy(shape.getCreatedBy())
                        .createdAt(shape.getCreatedAt().toString())
                        .version(shape.getVersion())
                        .area(shape.computeArea())
                        .perimeter(shape.computePerimeter())
                        .build())
                .collect(Collectors.toList());
    }
//  return shapes.stream()
//////                .map(shape -> new ShapeDto(shape.getId(), shape.getType(), shape.getVersion(), shape.getCreatedBy(), shape.getCreatedAt().toString(),
//////                        shape.getLastModifiedAt().toString(), shape.getLastModifiedBy(), shape.computeArea(), shape.computePerimeter()))
//////                .collect(Collectors.toList());
////        List<Shape> shapes = entityManager.createQuery(query).getResultList();
////
////        return shapes.stream()
////                .map(shape -> new ShapeDto(shape.getId(), shape.getType(), shape.getVersion(), shape.getCreatedBy(), shape.getCreatedAt().toString(),
////                        shape.getLastModifiedAt().toString(), shape.getLastModifiedBy(), shape.computeArea(), shape.computePerimeter()))
////                .collect(Collectors.toList());
////    }

    public List<Shape> filterShapes(FilterRequest filterRequest) {

        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCircle qCircle = QCircle.circle;
        QShape qShape = QShape.shape;
        QRectangle qRectangle = QRectangle.rectangle;
        QSquare qSquare = QSquare.square;

        List<BooleanExpression> expressions = new ArrayList<>();
        if (filterRequest.getRadiusFrom() != null) {
            expressions.add(qCircle.radius.goe(filterRequest.getRadiusFrom()));
        }
        if (filterRequest.getShapeType() != null) {
            expressions.add(qShape.type.eq(filterRequest.getShapeType()));
        }
        if (filterRequest.getRadiusTo() != null) {
            expressions.add(qCircle.radius.loe(filterRequest.getRadiusTo()));
        }
        if (filterRequest.getAreaFrom() != null) {
            expressions.add(QShapeView.shapeView.area.goe(filterRequest.getAreaFrom()));
        }
        if (filterRequest.getAreaTo() != null) {
            expressions.add(QShapeView.shapeView.area.loe(filterRequest.getAreaTo()));
        }
        if (filterRequest.getWidthFrom() != null) {
            expressions.add(qRectangle.width.goe(filterRequest.getWidthFrom()));
        }
        if (filterRequest.getWidthTo() != null) {
            expressions.add(qRectangle.width.loe(filterRequest.getWidthTo()));
        }
        if (filterRequest.getHeightFrom() != null) {
            expressions.add(qRectangle.height.goe(filterRequest.getHeightFrom()));
        }
        if (filterRequest.getHeightTo() != null) {
            expressions.add(qRectangle.height.loe(filterRequest.getHeightTo()));
        }
        if (filterRequest.getSideTo() != null) {
            expressions.add(qSquare.side.loe(filterRequest.getSideTo()));
        }
        if (filterRequest.getSideFrom() != null) {
            expressions.add(qSquare.side.goe(filterRequest.getSideFrom()));
        }

        BooleanExpression finalExpression = null;
        if (!expressions.isEmpty()) {
            finalExpression = expressions.stream()
                    .reduce(BooleanExpression::and)
                    .orElse(null);
        }

        JPAQuery<Shape> query = queryFactory.selectFrom(qShape);
        if (finalExpression != null) {
            query = query.where(finalExpression);
        }
        return query.fetch();
    }


    public List<Shape> ostatecznaFiltracja2(FilterRequest filterRequest) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCircle qCircle = QCircle.circle;
        QShape qShape = QShape.shape;
        QRectangle qRectangle = QRectangle.rectangle;
        QSquare qSquare = QSquare.square;
        QShapeView qShapeView = QShapeView.shapeView;

        // Inicjalizacja listy warunków
        List<Predicate> predicates = new ArrayList<>();

        // Dodanie warunku dla circle
        if (filterRequest.getRadiusFrom() != null) {
            Predicate circlePredicate = qCircle.radius.goe(filterRequest.getRadiusFrom());
            predicates.add(circlePredicate);
        }

        // Dodanie warunku dla shape
        if (filterRequest.getShapeType() != null) {
            Predicate shapePredicate = qShape.type.eq(filterRequest.getShapeType());
            predicates.add(shapePredicate);
        }

        // Dodanie warunku dla shapeView
        if (filterRequest.getAreaFrom() != null) {
            Predicate shapeViewPredicate = qShapeView.area.goe(filterRequest.getAreaFrom());
            predicates.add(shapeViewPredicate);
        }

        // Dodanie warunków dla rectangle
        if (filterRequest.getWidthFrom() != null) {
            Predicate rectangleWidthPredicate = qRectangle.width.goe(filterRequest.getWidthFrom());
            predicates.add(rectangleWidthPredicate);
        }

        if (filterRequest.getWidthTo() != null) {
            Predicate rectangleWidthPredicate = qRectangle.width.loe(filterRequest.getWidthTo());
            predicates.add(rectangleWidthPredicate);
        }

        if (filterRequest.getHeightFrom() != null) {
            Predicate rectangleHeightPredicate = qRectangle.height.goe(filterRequest.getHeightFrom());
            predicates.add(rectangleHeightPredicate);
        }

        if (filterRequest.getHeightTo() != null) {
            Predicate rectangleHeightPredicate = qRectangle.height.loe(filterRequest.getHeightTo());
            predicates.add(rectangleHeightPredicate);
        }

        // Dodanie warunków dla square
        if (filterRequest.getSideFrom() != null) {
            Predicate squareSidePredicate = qSquare.side.goe(filterRequest.getSideFrom());
            predicates.add(squareSidePredicate);
        }

        if (filterRequest.getSideTo() != null) {
            Predicate squareSidePredicate = qSquare.side.loe(filterRequest.getSideTo());
            predicates.add(squareSidePredicate);
        }

        // Połączenie wszystkich warunków w jedno wyrażenie typu Predicate
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//        Predicate finalPredicate = criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        // Wykonanie zapytania z wykorzystaniem utworzonego wyrażenia Predicate
//        List<Shape> shapeList = queryFactory.selectFrom(qShape).where(finalPredicate).fetch();

//        return shapeList;
        return null;
    }

    public List<Shape> nwm(FilterRequest filterRequest) {

//        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCircle qCircle = QCircle.circle;
        QShape qShape = QShape.shape;
        QRectangle qRectangle = QRectangle.rectangle;
        QSquare qSquare = QSquare.square;

        Stream<BooleanExpression> predicateStream = Stream.of(
                filterRequest.getRadiusFrom() != null ? qCircle.radius.goe(filterRequest.getRadiusFrom()) : null,
                filterRequest.getShapeType() != null ? qShape.type.eq(filterRequest.getShapeType()) : null,
                filterRequest.getRadiusTo() != null ? QCircle.circle.radius.doubleValue().eq(filterRequest.getRadiusTo()) : null,
                filterRequest.getAreaFrom() != null ? QShapeView.shapeView.area.goe(filterRequest.getAreaFrom()) : null,
                filterRequest.getAreaTo() != null ? QShapeView.shapeView.area.loe(filterRequest.getAreaTo()) : null,
                filterRequest.getWidthFrom() != null ? qRectangle.width.goe(filterRequest.getWidthFrom()) : null,
                filterRequest.getWidthTo() != null ? qRectangle.width.loe(filterRequest.getWidthTo()) : null,
                filterRequest.getHeightFrom() != null ? qRectangle.height.goe(filterRequest.getHeightFrom()) : null,
                filterRequest.getHeightTo() != null ? qRectangle.height.loe(filterRequest.getHeightTo()) : null,
                filterRequest.getSideTo() != null ? qSquare.side.loe(filterRequest.getSideTo()) : null,
                filterRequest.getSideFrom() != null ? qSquare.side.goe(filterRequest.getSideFrom()) : null
        ).filter(Objects::nonNull);

        BooleanExpression finalExpression = predicateStream
                .reduce(BooleanExpression::and)
                .orElse(null);
        return new JPAQuery<>(entityManager)
                .select(qShape)
                .from(qShape)
                .where(finalExpression)
                .fetch();
    }


    public List<Shape> ostatecznaFiltracja2200(FilterRequest filterRequest) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCircle qCircle = QCircle.circle;
        QShape qShape = QShape.shape;
        QRectangle qRectangle = QRectangle.rectangle;
        QSquare qSquare = QSquare.square;
        QShapeView qShapeView = QShapeView.shapeView;
        List<com.querydsl.core.types.Predicate> predicates = new ArrayList<>();

        if (filterRequest.getRadiusFrom() != null) {
            predicates.add(qCircle.radius.goe(filterRequest.getRadiusFrom()));
        }
        if (filterRequest.getRadiusTo() != null) {
            predicates.add(qCircle.radius.loe(filterRequest.getRadiusFrom()));
        }
        if (filterRequest.getShapeType() != null) {
            predicates.add(qShape.type.eq(filterRequest.getShapeType()));
        }
        if (filterRequest.getAreaFrom() != null) {
            predicates.add(qShapeView.area.goe(filterRequest.getAreaFrom()));
        }
        if (filterRequest.getAreaTo() != null) {
            predicates.add(qShapeView.area.loe(filterRequest.getAreaTo()));
        }
        if (filterRequest.getWidthFrom() != null) {
            predicates.add(qRectangle.width.goe(filterRequest.getWidthFrom()));
        }
        if (filterRequest.getWidthTo() != null) {
            predicates.add(qRectangle.width.goe(filterRequest.getWidthTo()));
        }
        if (filterRequest.getHeightFrom() != null) {
            predicates.add(qRectangle.height.goe(filterRequest.getHeightFrom()));
        }
        if (filterRequest.getHeightTo() != null) {
            predicates.add(qRectangle.height.goe(filterRequest.getHeightTo()));
        }
        if (filterRequest.getSideTo() != null) {
            predicates.add(qSquare.side.loe(filterRequest.getSideTo()));
        }
        if (filterRequest.getSideFrom() != null) {
            predicates.add(qSquare.side.goe(filterRequest.getSideFrom()));
        }

        com.querydsl.jpa.impl.JPAQuery<Shape> query = queryFactory.select(qShape)
                .from(qShape)
//                .leftJoin(qShape.circle, qCircle)
//                .leftJoin(qShape.rectangle, qRectangle)
//                .leftJoin(qShape.square, qSquare)
//                .leftJoin(qShape.shapeView, qShapeView)
                .where(predicates.toArray(new com.querydsl.core.types.Predicate[predicates.size()]));

        return query.fetch();
    }

    public List<Shape> ostatecznaFiltracja220(FilterRequest filterRequest) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCircle qCircle = QCircle.circle;
        QShape qShape = QShape.shape;
        QRectangle qRectangle = QRectangle.rectangle;
        QSquare qSquare = QSquare.square;
        QShapeView qShapeView = QShapeView.shapeView;
        List<Predicate> predicates = new ArrayList<>();

        if (filterRequest.getRadiusFrom() != null) {
            predicates.add(qCircle.radius.goe(filterRequest.getRadiusFrom()));
        }
        if (filterRequest.getRadiusTo() != null) {
            predicates.add(qCircle.radius.loe(filterRequest.getRadiusFrom()));
        }
        if (filterRequest.getShapeType() != null) {
            predicates.add(qShape.type.eq(filterRequest.getShapeType()));
        }
        if (filterRequest.getAreaFrom() != null) {
            predicates.add(qShapeView.area.goe(filterRequest.getAreaFrom()));
        }
        if (filterRequest.getAreaTo() != null) {
            predicates.add(qShapeView.area.loe(filterRequest.getAreaTo()));
        }
        if (filterRequest.getWidthFrom() != null) {
            predicates.add(qRectangle.width.goe(filterRequest.getWidthFrom()));
        }
        if (filterRequest.getWidthTo() != null) {
            predicates.add(qRectangle.width.goe(filterRequest.getWidthTo()));
        }
        if (filterRequest.getHeightFrom() != null) {
            predicates.add(qRectangle.height.goe(filterRequest.getHeightFrom()));
        }
        if (filterRequest.getHeightTo() != null) {
            predicates.add(qRectangle.height.goe(filterRequest.getHeightTo()));
        }
        if (filterRequest.getSideTo() != null) {
            predicates.add(qSquare.side.loe(filterRequest.getSideTo()));
        }
        if (filterRequest.getSideFrom() != null) {
            predicates.add(qSquare.side.goe(filterRequest.getSideFrom()));
        }

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shape> query = builder.createQuery(Shape.class);
//        query.where(builder.and((javax.persistence.criteria.Predicate) predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();
    }
    public List<Shape> ostatecznaFiltracja(FilterRequest filterRequest) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QCircle qCircle = QCircle.circle;
        QShape qShape = QShape.shape;
        QRectangle qRectangle = QRectangle.rectangle;
        QSquare qSquare = QSquare.square;
        QShapeView qShapeView = QShapeView.shapeView;
//        filterRequest.getAreaFrom() != null ? queryFactory.selectFrom(qShapeView).where(qShapeView.shape..goe(filterRequest.getAreaFrom())) : null,

                List<Predicate> list = (List<Predicate>) Stream.of(
                        filterRequest.getRadiusFrom() != null ? queryFactory.selectFrom(qCircle).where(qCircle.radius.goe(filterRequest.getRadiusFrom())) : null,
                        filterRequest.getRadiusTo() != null ? queryFactory.selectFrom(qCircle).where(qCircle.radius.loe(filterRequest.getRadiusFrom())) : null,
                        filterRequest.getShapeType() != null ? queryFactory.selectFrom(qShapeView).where(qShapeView.shape.type.eq(filterRequest.getShapeType())) : null,
                        filterRequest.getAreaFrom() != null ? queryFactory.selectFrom(qShapeView).where(qShapeView.area.goe(filterRequest.getAreaFrom())) : null,
                        filterRequest.getAreaTo() != null ? queryFactory.selectFrom(qShapeView).where(qShapeView.area.loe(filterRequest.getAreaTo())) : null,
                        filterRequest.getWidthFrom() != null ? queryFactory.selectFrom(qRectangle).where(qRectangle.width.goe(filterRequest.getWidthFrom())) : null,
                        filterRequest.getWidthTo() != null ? queryFactory.selectFrom(qRectangle).where(qRectangle.width.goe(filterRequest.getWidthTo())) : null,
                        filterRequest.getHeightFrom() != null ? queryFactory.selectFrom(qRectangle).where(qRectangle.height.goe(filterRequest.getHeightFrom())) : null,
                        filterRequest.getHeightTo() != null ? queryFactory.selectFrom(qRectangle).where(qRectangle.height.goe(filterRequest.getHeightTo())) : null,
                        filterRequest.getSideTo() != null ? queryFactory.selectFrom(qSquare).where(qSquare.side.loe(filterRequest.getSideTo())) : null,
                        filterRequest.getSideFrom() != null ? queryFactory.selectFrom(qSquare).where(qSquare.side.goe(filterRequest.getSideFrom())) : null)
                .filter(Objects::nonNull);
//                .collect(Collectors.toList());
        Predicate[] predicates = list.toArray(Predicate[]::new);
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Shape> query = builder.createQuery(Shape.class);
//        Predicate finalPredicate = builder.and(predicates);
//        query.where(finalPredicate);
        return entityManager.createQuery(query).getResultList();
    }
//    public List<Shape> ostatecznaFiltracja(FilterRequest filterRequest) {
//        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
//        QCircle qCircle = QCircle.circle;
//        QShape qShape = QShape.shape;
//        QRectangle qRectangle = QRectangle.rectangle;
//        QSquare qSquare = QSquare.square;
//        QShapeView qShapeView = QShapeView.shapeView;
//        List<Predicate> list = (List<Predicate>) Stream.of(
//
//                filterRequest.getRadiusFrom() != null ? queryFactory.selectFrom(qCircle).where(qCircle.radius.goe(filterRequest.getRadiusFrom())) : null,
//                filterRequest.getRadiusTo() != null ? queryFactory.selectFrom(qCircle).where(qCircle.radius.loe(filterRequest.getRadiusFrom())) : null,
//                filterRequest.getShapeType() != null ? queryFactory.selectFrom(qShape).where(qShape.type.eq(filterRequest.getShapeType())) : null,
//                filterRequest.getAreaFrom() != null ? queryFactory.selectFrom(qShapeView).where(qShapeView.area.goe(filterRequest.getAreaFrom())) : null,
//                filterRequest.getAreaTo() != null ? queryFactory.selectFrom(qShapeView).where(qShapeView.area.loe(filterRequest.getAreaTo())) : null,
//                filterRequest.getWidthFrom() != null ? queryFactory.selectFrom(qRectangle).where(qRectangle.width.goe(filterRequest.getWidthFrom())) : null,
//                filterRequest.getWidthTo() != null ? queryFactory.selectFrom(qRectangle).where(qRectangle.width.goe(filterRequest.getWidthTo())) : null,
//                filterRequest.getHeightFrom() != null ? queryFactory.selectFrom(qRectangle).where(qRectangle.height.goe(filterRequest.getHeightFrom())) : null,
//                filterRequest.getHeightTo() != null ? queryFactory.selectFrom(qRectangle).where(qRectangle.height.goe(filterRequest.getHeightTo())) : null,
//                filterRequest.getSideTo() != null ? queryFactory.selectFrom(qSquare).where(qSquare.side.loe(filterRequest.getSideTo())) : null,
//                filterRequest.getSideFrom() != null ? queryFactory.selectFrom(qSquare).where(qSquare.side.goe(filterRequest.getSideFrom())) : null);
//        Predicate[] predicates = list.toArray(new Predicate[0]);
//        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
//        CriteriaQuery<Shape> query = builder.createQuery(Shape.class);
//        query.where(builder.and(predicates));
//        Predicate finalPredicate = builder.and(predicates.toArray(new Predicate[0]));
//        query.where(finalPredicate);
//
//        return entityManager.createQuery(query).getResultList();


//        query.where(builder.and(predicates));
//        Predicate finalPredicate = builder.and(predicates(new Predicate[0]));
//        return entityManager.createQuery(query).getResultList();
//    }


//        Root<Shape> root = query.from(Shape.class);
//        queryFactory.selectFrom(qRectangle)
//                .innerJoin(qRectangle.shape, qShape)
//                .where(qShape.type.eq(filterRequest.getShapeType()))
//                .fetch();
//        List<Shape> shapes = queryFactory.selectFrom(qRectangle, qCircle, qSquare)
//                .where((Predicate) list)
//                .fetch();


//        query.where(builder.and(predicates));
//        Predicate finalPredicate = builder.and(predicates(new Predicate[0]));
//        return entityManager.createQuery(query).getResultList();}
//}
public List<Shape> filter4(FilterRequest filterRequest){

    QCircle qCircle = QCircle.circle;
    QShape qShape = QShape.shape;
    QRectangle qRectangle = QRectangle.rectangle;
    QSquare qSquare = QSquare.square;
    QShapeView qShapeView = QShapeView.shapeView;

    List<BooleanExpression> expressions = Stream.of(
                    filterRequest.getRadiusFrom() != null ? qCircle.radius.goe(filterRequest.getRadiusFrom()) : null,
                    filterRequest.getShapeType() != null ? qShapeView.shape.type.eq(filterRequest.getShapeType()) : null,
//                    filterRequest.getRadiusTo() != null ? qCircle.radius.eq(filterRequest.getRadiusTo()) : null,
                    filterRequest.getRadiusTo() != null ? qCircle.radius.eq(filterRequest.getRadiusTo()) : null,
                    filterRequest.getAreaFrom() != null ? QShapeView.shapeView.area.goe(filterRequest.getAreaFrom()) : null,
                    filterRequest.getAreaTo() != null ? QShapeView.shapeView.area.loe(filterRequest.getAreaTo()) : null,
                    filterRequest.getWidthFrom() != null ? qRectangle.width.goe(filterRequest.getWidthFrom()) : null,
                    filterRequest.getWidthTo() != null ? qRectangle.width.loe(filterRequest.getWidthTo()) : null,
                    filterRequest.getHeightFrom() != null ? qRectangle.height.goe(filterRequest.getHeightFrom()) : null,
                    filterRequest.getHeightTo() != null ? qRectangle.height.loe(filterRequest.getHeightTo()) : null,
                    filterRequest.getSideTo() != null ? qSquare.side.loe(filterRequest.getSideTo()) : null,
                    filterRequest.getSideFrom() != null ? qSquare.side.goe(filterRequest.getSideFrom()) : null)
            .filter(Objects::nonNull)
            .toList();

    BooleanExpression finalExpression = expressions.stream()
            .reduce(BooleanExpression::and)
            .orElse(null);
    return new JPAQuery<>(entityManager)
            .select(qShape)
            .from(qShape)
            .where(finalExpression)
            .fetch();
}

public List<Shape> filter3(FilterRequest filterRequest){
    JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
    QCircle circle = QCircle.circle;
    QCircle qCircle = QCircle.circle;

    List<Circle> circles = queryFactory.selectFrom(circle).where(circle.radius.goe( filterRequest.getRadiusFrom())).fetch();
    List<Shape> shapeList = new ArrayList<>();
    shapeList.add(circles.get(0));
return shapeList;
}
    public List<Shape> filter2(FilterRequest filterRequest) {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QShape shape = QShape.shape;
        System.out.println(qCircle.radius);
        List<BooleanExpression> expressions = Stream.of(
                        filterRequest.getRadiusFrom() != null ? qCircle.radius.goe(filterRequest.getRadiusFrom()) : null,
                        filterRequest.getShapeType() != null ? qShape.type.eq(filterRequest.getShapeType()) : null,
                        filterRequest.getRadiusTo() != null ? QCircle.circle.radius.doubleValue().eq(filterRequest.getRadiusTo()) : null,
                        filterRequest.getAreaFrom() != null ? QShapeView.shapeView.area.goe(filterRequest.getAreaFrom()) : null,
                        filterRequest.getAreaTo() != null ? QShapeView.shapeView.area.loe(filterRequest.getAreaTo()) : null,
                        filterRequest.getWidthFrom() != null ? qRectangle.width.goe(filterRequest.getWidthFrom()) : null,
                        filterRequest.getWidthTo() != null ? qRectangle.width.loe(filterRequest.getWidthTo()) : null,
                        filterRequest.getHeightFrom() != null ? qRectangle.height.goe(filterRequest.getHeightFrom()) : null,
                        filterRequest.getHeightTo() != null ? qRectangle.height.loe(filterRequest.getHeightTo()) : null,

                        filterRequest.getSideTo() != null ? qSquare.side.loe(filterRequest.getSideTo()) : null,
                        filterRequest.getSideFrom() != null ? qSquare.side.goe(filterRequest.getSideFrom()) : null)
                .filter(Objects::nonNull)
                .toList();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        if (filterRequest.getRadiusFrom() != null) {
            booleanBuilder.and(qCircle.radius.goe(filterRequest.getRadiusFrom()));
        }
        if (filterRequest.getRadiusTo() != null) {
            booleanBuilder.and(qCircle.radius.loe(filterRequest.getRadiusTo()));
        }
// reszta wyrażeń z listy expressions


        BooleanExpression finalExpression = expressions.stream()
                .reduce(BooleanExpression::and)
                .orElse(null);
//                        filterRequest.getRadiusTo() != null ? queryFactory.selectFrom(qCircle).where(qCircle.radius.goe( filterRequest.getRadiusFrom())) : null,
        return new JPAQuery<>(entityManager)
                .select(shape)
                .from(shape)
                .where(finalExpression)
                .fetch();
    }

//        BooleanExpression areaFromExpression = filterRequest.getAreaFrom() != null ? shapeView.area.goe(filterRequest.getAreaFrom()) : null;
//        BooleanExpression areaToExpression = filterRequest.getAreaTo() != null ? shapeView.area.loe(filterRequest.getAreaTo()) : null;
//        BooleanExpression widthFromExpression = filterRequest.getWidthFrom() != null ? QRectangle.rectangle.width.goe(filterRequest.getWidthFrom()) : null;
//        BooleanExpression widthToExpression = filterRequest.getWidthTo() != null ? QRectangle.rectangle.width.loe(filterRequest.getWidthTo()) : null;
//        BooleanExpression heightFromExpression = filterRequest.getHeightFrom() != null ? QRectangle.rectangle.height.goe(filterRequest.getHeightFrom()) : null;
//        BooleanExpression heightToExpression = filterRequest.getHeightTo() != null ? QRectangle.rectangle.height.loe(filterRequest.getHeightTo()) : null;
//
////        BooleanExpression finalExpression = areaFromExpression.and(areaToExpression).and(widthFromExpression)
////                .and(widthToExpression).and(heightFromExpression).and(heightToExpression);
//
//        List<BooleanExpression> expressions = Stream.of(
//                        filterRequest.getRadiusFrom() != null ? circle.radius.goe(filterRequest.getRadiusFrom()) : null,
//                        filterRequest.getRadiusTo() != null ? circle.radius.goe(filterRequest.getRadiusTo()) : null
//                        // i tak dalej
//                )
//                .filter(Objects::nonNull)
//                .collect(Collectors.toList());
//        BooleanExpression finalExpression = Expressions.allOf((BooleanExpression) expressions);
//
//        List<Shape> result = new JPAQuery<>(entityManager)
//                .select(shape)
//                .from(shape)
//                .where(finalExpression)
//                .fetch();
//
//        return result;
//    }
//        BooleanExpression areaFromExpression = filterRequest.getAreaFrom() != null ? shapeView.area.goe(filterRequest.getAreaFrom()) : null;
//        BooleanExpression areaToExpression = filterRequest.getAreaTo() != null ? shapeView.area.goe(filterRequest.getAreaTo()) : null;
//        BooleanExpression perimeterFromExpression = filterRequest.getPerimeterFrom() != null ? shapeView.perimeter.goe(filterRequest.getPerimeterFrom()) : null;
//        BooleanExpression perimeterToExpression = filterRequest.getPerimeterTo() != null ? shapeView.perimeter.loe(filterRequest.getPerimeterTo()) : null;
//
//        BooleanExpression finalExpression = areaFromExpression.and(areaToExpression);
//        if (perimeterFromExpression != null) {
//            finalExpression = (finalExpression != null) ? finalExpression.and(perimeterFromExpression) : perimeterFromExpression;
//        }
//        if (perimeterToExpression != null) {
//            finalExpression = (finalExpression != null) ? finalExpression.and(perimeterToExpression) : perimeterToExpression;
//        }
//
//        return new JPAQuery<>(entityManager)
//                .select(shape)
//                .from(shape)
//                .leftJoin(shapeView).on(shape.id.eq(shapeView.id))
//                .where(finalExpression)
//                .fetch();
//    }
//    public List<Shape> filter(FilterRequest filterRequest) {
//        BooleanExpression circleFromExpression = filterRequest.getRadiusFrom() != null ? circle.radius.goe(filterRequest.getRadiusFrom()): null;
//        BooleanExpression radiusFromExpression = filterRequest.getRadiusTo() != null ? circle.radius.goe(filterRequest.getRadiusTo()) :null;

//QRequest vr = QRequest.request;
//BooleanBuilder builder = new BooleanBuilder();
//
//QRObject obj = vr.obj.as(QRObject.class);
//builder.and(obj.street.fullTitle.containsIgnoreCase(filter.getAddress()));

//        BooleanExpression areaFromExpression = filterRequest.getAreaFrom() != null ? QShapeView.shapeView.area.goe(filterRequest.getAreaFrom()) : null;
//        BooleanExpression widthFromExpression = filterRequest.getWidthFrom() != null ? QRectangle.rectangle.width.goe(filterRequest.getWidthFrom()) : null;
//        BooleanExpression widthToExpression = filterRequest.getWidthTo() != null ? QRectangle.rectangle.width.loe(filterRequest.getWidthTo()) : null;
//        BooleanExpression heightFromExpression = filterRequest.getHeightFrom() != null ? QRectangle.rectangle.height.goe( filterRequest.getHeightFrom()) : null;
//        BooleanExpression heightToExpression = filterRequest.getHeightTo() != null ? QRectangle.rectangle.height.loe(filterRequest.getHeightTo()) : null;

//        BooleanExpression finalExpression = areaFromExpression.and(widthFromExpression).and(widthToExpression).and(heightToExpression)
//                .and(heightFromExpression);
//        BooleanExpression finalExpression = circleFromExpression.and(radiusFromExpression);
//
//        return new JPAQuery<>(entityManager)
//                .select(shape)
//                .from(shape)
//                .where(finalExpression)
//                .fetch();
//    }

    //for (
//    Field field : filterRequest.getClass().getDeclaredFields()) {
//        Map<String, Function<Object, Boolean>> filters = new HashMap<>();
//    filters.put("createdBy", qShape.createdBy::equalsIgnoreCase);
//    filters.put("shapeType", qShape.type::equalsIgnoreCase);
//    filters.put("areaFrom", qShapeView.area::goe);
//    filters.put("areaTo", qShapeView.area::loe);
//    filters.put("perimeterFrom", qShapeView.perimeter::goe);
//    filters.put("perimeterTo", qShapeView.perimeter::loe);
//    filters.put("createdAtFrom", qShape.createdAt::goe);
//    filters.put("createdAtTo", qShape.createdAt::loe);
//    filters.put("version", qShape.version::eq);
//    filters.put("sideFrom", qSquare.side::goe);
//    filters.put("sideTo", qSquare.side::loe);
//    filters.put("widthFrom", qRectangle.width::goe);
//    filters.put("widthTo", qRectangle.width::loe);
//    filters.put("heightFrom", qRectangle.height::goe);
//    filters.put("heightTo", qRectangle.height::loe);
//    filters.put("radiusFrom", qCircle.radius::goe);
//    filters.put("radiusTo", qCircle.radius::loe);
//        filters.put("createdBy", value -> QShape.shape.createdBy.eq((String) value));
//        filters.put("shapeType", value -> QShape.shape.type.eq((String) value));
//        filters.put("areaFrom", value -> QShape.shapeView.area.goe((Double) value));
//        filters.put("areaTo", value -> QShape.shapeView.area.loe((Double) value));
//        filters.put("perimeterFrom", value -> QShape.shapeView.perimeter.goe((Double) value));
//        filters.put("perimeterTo", value -> QShape.shapeView.perimeter.loe((Double) value));
//        filters.put("createdAtFrom", value -> QShape.shape.createdAt.goe((LocalDateTime) value));
//        filters.put("createdAtTo", value -> QShape.shape.createdAt.loe((LocalDateTime) value));
//        filters.put("version", value -> QShape.shape.version.eq((Long) value));
//        filters.put("sideFrom", value -> QSquare.square.side.goe((Double) value));
//        filters.put("sideTo", value -> QSquare.square.side.loe((Double) value));
//        filters.put("widthFrom", value -> QRectangle.rectangle.width.goe((Double) value));
//        filters.put("widthTo", value -> QRectangle.rectangle.width.loe((Double) value));
//        filters.put("heightFrom", value -> QRectangle.rectangle.height.goe((Double) value));
//        filters.put("heightTo", value -> QRectangle.rectangle.height.loe((Double) value));
//        filters.put("radiusFrom", value -> QCircle.circle.radius.goe((Double) value));
//        filters.put("radiusTo", value -> QCircle.circle.radius.loe((Double) value));
//        field.setAccessible(true);
//        String fieldName = field.getName();
//        Object fieldValue = field.get(filterRequest);
//        if (fieldValue != null) {
//            Function<Object, Boolean> filter = filters.get(fieldName);
//            if (filter != null) {
//                Boolean expression = filter.apply(fieldValue);
//                if (expression != null) {
//                    if (fieldName.endsWith("From")) {
//                        predicate = predicate == null ? expression : predicate.and(expression);
//                    } else if (fieldName.endsWith("To")) {
//                        predicate = predicate == null ? expression : predicate.and(expression);
//                    } else {
//                        predicate = predicate == null ? expression : predicate.and(expression);
//                    }
//                }
//            }
//        }
//    }
//
//    List<Shape> shapes = (List<Shape>) query.from(qShape).leftJoin(qShape.shapeView, qShapeView)
//            .where(predicate);
//}
}
