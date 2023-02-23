package com.example.shapesecurity.strategy.dtoMapper;

import com.example.shapesecurity.mapper.ShapeMapper;
import com.example.shapesecurity.model.dto.RectangleDto;
import com.example.shapesecurity.model.dto.ShapeDto;
import com.example.shapesecurity.model.shape.Rectangle;
import com.example.shapesecurity.model.shape.Shape;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component("RECTANGLEDTO")
@RequiredArgsConstructor
public class RectangleMapper implements ShapeDtoMapper {
    private final ShapeMapper shapeMapper;
    @Override
    public ShapeDto mapShapeToShapeDto(Shape shape) {
        Rectangle rectangle = (Rectangle) shape;
        RectangleDto rectangleDto = shapeMapper.fromRectangle(rectangle);
        rectangleDto.setArea(rectangle.computeArea());
        rectangleDto.setPerimeter(rectangle.computePerimeter());
        return rectangleDto;
    }
}
