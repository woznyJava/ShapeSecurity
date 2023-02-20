package com.example.shapesecurity.strategy.dtoMapper;

import com.example.shapesecurity.mapper.ShapeMapper;
import com.example.shapesecurity.model.dto.RectangleDto;
import com.example.shapesecurity.model.dto.ShapeDto;
import com.example.shapesecurity.model.shape.Rectangle;
import com.example.shapesecurity.model.shape.Shape;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("RECTANGLEDTO")
public class RectangleMapper implements ShapeDtoMapper {
    @Autowired
    private ShapeMapper shapeMapper;

    @Override
    public ShapeDto mapShapeToShapeDto(Shape shape) {
        Rectangle rectangle = (Rectangle) shape;
        RectangleDto rectangleDto = shapeMapper.fromRectangle(rectangle);
        rectangleDto.setArea(rectangle.computeArea());
        rectangleDto.setPerimeter(rectangle.computePerimeter());
        return rectangleDto;
    }
}
