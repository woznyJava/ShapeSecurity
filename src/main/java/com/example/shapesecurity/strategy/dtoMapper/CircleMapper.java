package com.example.shapesecurity.strategy.dtoMapper;

import com.example.shapesecurity.mapper.ShapeMapper;
import com.example.shapesecurity.model.dto.CircleDto;
import com.example.shapesecurity.model.dto.ShapeDto;
import com.example.shapesecurity.model.shape.Circle;
import com.example.shapesecurity.model.shape.Shape;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component("CIRCLEDTO")
public class CircleMapper implements ShapeDtoMapper {
    private final ShapeMapper shapeMapper;

    @Override
    public ShapeDto mapShapeToShapeDto(Shape shape) {

        Circle circle = (Circle) shape;
        CircleDto circleDto = shapeMapper.fromCircle(circle);
        circleDto.setArea(circle.computeArea());
        circleDto.setPerimeter(circle.computePerimeter());
        return circleDto;
    }
}
