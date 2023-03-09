package com.example.shapesecurity.shapeBuilderService;

import com.example.shapesecurity.mapper.ShapeMapper;
import com.example.shapesecurity.mapper.ShapeMapperImpl;
import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.dto.CircleDto;
import com.example.shapesecurity.model.dto.RectangleDto;
import com.example.shapesecurity.model.dto.SquareDto;
import com.example.shapesecurity.model.shape.*;
import com.example.shapesecurity.service.impl.ShapeBuildServiceImpl;
import com.example.shapesecurity.strategy.builder.CircleBuilder;
import com.example.shapesecurity.strategy.builder.RectangleBuilder;
import com.example.shapesecurity.strategy.builder.ShapeBuilder;
import com.example.shapesecurity.strategy.builder.SquareBuilder;
import com.example.shapesecurity.strategy.dtoMapper.CircleMapper;
import com.example.shapesecurity.strategy.dtoMapper.RectangleMapper;
import com.example.shapesecurity.strategy.dtoMapper.ShapeDtoMapper;
import com.example.shapesecurity.strategy.dtoMapper.SquareMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ShapeBuilderServiceTests {
    private ShapeBuildServiceImpl shapeBuildService;
    private ShapeMapper shapeMapper = new ShapeMapperImpl();

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        Map<String, ShapeBuilder> shapeBuilderMap = new HashMap<>();
        shapeBuilderMap.put("CIRCLE", new CircleBuilder());
        shapeBuilderMap.put("RECTANGLE", new RectangleBuilder());
        shapeBuilderMap.put("SQUARE", new SquareBuilder());

        Map<String, ShapeDtoMapper> shapeMapperMap = new HashMap<>();
        shapeMapperMap.put("CIRCLEDTO", new CircleMapper(shapeMapper));
        shapeMapperMap.put("RECTANGLEDTO", new RectangleMapper(shapeMapper));
        shapeMapperMap.put("SQUAREDTO", new SquareMapper(shapeMapper));
        shapeBuildService = new ShapeBuildServiceImpl(shapeBuilderMap, shapeMapperMap);
    }

    @Test
    public void shouldBuildCircle() {
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("radius", 5.0);

        CreateShapeCommand createShapeCommand = new CreateShapeCommand("CIRCLE", parameters);
        Map<String, Object> map = shapeBuildService.buildShape(createShapeCommand);
        Circle circle = (Circle) map.get("Shape");
        ShapeView shapeView = (ShapeView) map.get("ShapeView");

        assertEquals(circle.getRadius(), parameters.get("radius"));
        assertEquals(shapeView.getRadius(), circle.getRadius());
        assertEquals(shapeView.getArea(), circle.computeArea());
        assertEquals(shapeView.getPerimeter(), circle.computePerimeter());
    }

    @Test
    public void shouldBuildSquare() {
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("side", 4.0);

        CreateShapeCommand createShapeCommand = new CreateShapeCommand("SQUARE", parameters);
        Map<String, Object> map = shapeBuildService.buildShape(createShapeCommand);

        Square square = (Square) map.get("Shape");
        ShapeView shapeView =  (ShapeView) map.get("ShapeView");

        assertEquals(shapeView.getSide(), square.getSide());
        assertEquals(shapeView.getArea(), square.computeArea());
        assertEquals(shapeView.getPerimeter(), square.computePerimeter());
        assertEquals(square.getSide(), parameters.get("side"));

    }

    @Test
    public void shouldBuildRectangle() {
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("width", 5.0);
        parameters.put("height", 6.0);

        CreateShapeCommand createShapeCommand = new CreateShapeCommand("RECTANGLE", parameters);
        Map<String, Object> map = shapeBuildService.buildShape(createShapeCommand);

        Rectangle rectangle = (Rectangle) map.get("Shape");
        ShapeView shapeView =  (ShapeView) map.get("ShapeView");

        assertEquals(shapeView.getHeight(), rectangle.getHeight());
        assertEquals(shapeView.getWidth(), rectangle.getWidth());
        assertEquals(shapeView.getArea(), rectangle.computeArea());
        assertEquals(shapeView.getPerimeter(), rectangle.computePerimeter());
        assertEquals(rectangle.getWidth(), parameters.get("width"));
        assertEquals(rectangle.getHeight(), parameters.get("height"));
    }

    @Test
    public void shouldBuildCircleDto() {
        Circle circle = new Circle(5.0);
        circle.setType("CIRCLE");

        CircleDto circleDto = (CircleDto) shapeBuildService.buildShapeDto(circle);

        assertEquals(circle.getRadius(), circleDto.getRadius());
        assertEquals(circle.computePerimeter(), circleDto.getPerimeter());
        assertEquals(circle.computeArea(), circleDto.getArea());
    }

    @Test
    public void shouldBuildSquareDto() {
        Square square = new Square(5.0);
        square.setType("SQUARE");

        SquareDto squareDto2 = (SquareDto) shapeBuildService.buildShapeDto(square);

        assertEquals(square.getSide(), squareDto2.getSide());
        assertEquals(square.computePerimeter(), squareDto2.getPerimeter());
        assertEquals(square.computeArea(), squareDto2.getArea());
    }

    @Test
    public void shouldBuildRectangleDto() {
        Rectangle rectangle = new Rectangle(5.0, 6.0);
        rectangle.setType("RECTANGLE");

        RectangleDto rectangleDto = (RectangleDto) shapeBuildService.buildShapeDto(rectangle);

        assertEquals(rectangle.getHeight(), rectangleDto.getHeight());
        assertEquals(rectangle.getWidth(), rectangleDto.getWidth());
        assertEquals(rectangle.computePerimeter(), rectangleDto.getPerimeter());
        assertEquals(rectangle.computeArea(), rectangleDto.getArea());
    }
}
