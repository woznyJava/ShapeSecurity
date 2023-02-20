package com.example.shapesecurity.shapeMapper;

import com.example.shapesecurity.mapper.ShapeMapper;
import com.example.shapesecurity.mapper.ShapeMapperImpl;
import com.example.shapesecurity.model.dto.CircleDto;
import com.example.shapesecurity.model.dto.RectangleDto;
import com.example.shapesecurity.model.dto.SquareDto;
import com.example.shapesecurity.model.shape.Circle;
import com.example.shapesecurity.model.shape.Rectangle;
import com.example.shapesecurity.model.shape.Square;
import com.example.shapesecurity.strategy.dtoMapper.CircleMapper;
import com.example.shapesecurity.strategy.dtoMapper.RectangleMapper;
import com.example.shapesecurity.strategy.dtoMapper.SquareMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ShapeDtoMapperTests {

    @InjectMocks
    private CircleMapper circleMapper;

    @InjectMocks
    private SquareMapper squareMapper;

    @InjectMocks
    private RectangleMapper rectangleMapper;

    @Spy
    private ShapeMapper shapeMapper = new ShapeMapperImpl();

    @Test
    public void mapShapeToShapeDto_ShouldReturnCircleDto() {
        Circle circle = new Circle(5.0);

        CircleDto circleDto = (CircleDto) circleMapper.mapShapeToShapeDto(circle);

        assertEquals(circle.getRadius(), circleDto.getRadius());
        assertEquals(circle.getType(), circleDto.getType());
        assertEquals(circle.computeArea(), circleDto.getArea());
        assertEquals(circle.computePerimeter(), circleDto.getPerimeter());
    }

    @Test
    public void mapShapeToShapeDto_ShouldReturnSquareDto() {
        Square square = new Square(6.0);

        SquareDto squareDto = (SquareDto) squareMapper.mapShapeToShapeDto(square);

        assertEquals(square.getSide(), squareDto.getSide());
        assertEquals(square.getType(), squareDto.getType());
        assertEquals(square.computeArea(), squareDto.getArea());
        assertEquals(square.computePerimeter(), squareDto.getPerimeter());
    }

    @Test
    public void mapShapeToShapeDto_ShouldReturnRectangleDto() {
        Rectangle rectangle = new Rectangle(7.0, 8.0);

        RectangleDto rectangleDto = (RectangleDto) rectangleMapper.mapShapeToShapeDto(rectangle);

        assertEquals(rectangle.getHeight(), rectangleDto.getHeight());
        assertEquals(rectangle.getWidth(), rectangleDto.getWidth());
        assertEquals(rectangle.getType(), rectangleDto.getType());
        assertEquals(rectangle.computeArea(), rectangleDto.getArea());
        assertEquals(rectangle.computePerimeter(), rectangleDto.getPerimeter());
    }
}
