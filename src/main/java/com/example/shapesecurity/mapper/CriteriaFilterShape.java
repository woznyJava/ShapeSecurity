package com.example.shapesecurity.mapper;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.dto.ShapeDto;
import com.example.shapesecurity.model.shape.QShape;
import com.example.shapesecurity.model.shape.QShapeView;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class CriteriaFilterShape {
    private final EntityManager entityManager;

    public List<ShapeDto> testNowy(FilterRequest filterRequest) {
        QShape qShape = QShape.shape;
        QShapeView qShapeView = QShapeView.shapeView;
//
//        List<BooleanExpression> expressions = Stream.of(
//                        filterRequest.getCreatedBy() != null ? qShapeView.shape.createdBy.eq(filterRequest.getCreatedBy()) : null,
//                        filterRequest.getShapeType() != null ? qShapeView.shape.type.eq(filterRequest.getShapeType()) : null,
//                        filterRequest.getAreaFrom() != null ? QShapeView.shapeView.area.goe(filterRequest.getAreaFrom()) : null,
//                        filterRequest.getAreaTo() != null ? QShapeView.shapeView.area.loe(filterRequest.getAreaTo()) : null,
//                        filterRequest.getPerimeterFrom() != null ? QShapeView.shapeView.perimeter.goe(filterRequest.getPerimeterFrom()) : null,
//                        filterRequest.getPerimeterTo() != null ? QShapeView.shapeView.perimeter.loe(filterRequest.getPerimeterTo()) : null,
//                        filterRequest.getCreatedAtFrom() != null ? qShapeView.shape.createdAt.goe(filterRequest.getCreatedAtFrom()) : null,
//                        filterRequest.getCreatedAtTo() != null ? qShapeView.shape.createdAt.loe(filterRequest.getCreatedAtTo()) : null,
//                        filterRequest.getVersion() != null ? qShapeView.shape.version.eq(filterRequest.getVersion()) : null,
//                        filterRequest.getRadiusFrom() != null ? qShapeView.shape.type.in("CIRCLE").and(qShapeView.radius.goe(filterRequest.getRadiusFrom())) : null,
//                        filterRequest.getRadiusTo() != null ? qShapeView.shape.type.in("CIRCLE").and(qShapeView.radius.loe(filterRequest.getRadiusTo())) : null,
//                        filterRequest.getWidthFrom() != null ? qShapeView.shape.type.in("RECTANGLE").and(qShapeView.width.goe(filterRequest.getWidthFrom())) : null,
//                        filterRequest.getWidthTo() != null ? qShapeView.shape.type.in("RECTANGLE").and(qShapeView.width.loe(filterRequest.getWidthTo())) : null,
//                        filterRequest.getHeightFrom() != null ? qShapeView.shape.type.in("RECTANGLE").and(qShapeView.height.goe(filterRequest.getHeightFrom())) : null,
//                        filterRequest.getHeightTo() != null ? qShapeView.shape.type.in("RECTANGLE").and(qShapeView.height.loe(filterRequest.getHeightTo())) : null,
//                        filterRequest.getSideTo() != null ? qShapeView.shape.type.in("SQUARE").and(qShapeView.side.loe(filterRequest.getSideTo())) : null,
//                        filterRequest.getSideFrom() != null ? qShapeView.shape.type.in("SQUARE").and(qShapeView.side.goe(filterRequest.getSideFrom())) : null)
//                .filter(Objects::nonNull)
//                .toList();
//
//        BooleanExpression finalExpression = expressions.stream()
//                .reduce(BooleanExpression::and)
//                .orElse(null);
//        return new JPAQuery<>(entityManager)
//                .select(qShape)
//                .from(qShape)
//                .where(finalExpression)
//                .fetch().stream()
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
        return null;
    }
}
