package com.example.shapesecurity.shapeBuilderService;

import com.example.shapesecurity.mapper.ShapeMapper;
import com.example.shapesecurity.mapper.ShapeMapperImpl;
import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.dto.CircleDto;
import com.example.shapesecurity.model.dto.RectangleDto;
import com.example.shapesecurity.model.dto.ShapeDto;
import com.example.shapesecurity.model.dto.SquareDto;
import com.example.shapesecurity.model.shape.Circle;
import com.example.shapesecurity.model.shape.Rectangle;
import com.example.shapesecurity.model.shape.ShapeView;
import com.example.shapesecurity.model.shape.Square;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        shapeBuildService = new ShapeBuildServiceImpl(shapeBuilderMap, shapeMapperMap, shapeMapper);
    }

    @Test
    public void shouldBuildCircle() {
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("radius", 5.0);

        CreateShapeCommand createShapeCommand = new CreateShapeCommand("CIRCLE", parameters);
        Circle circle = (Circle) shapeBuildService.buildShape(createShapeCommand);

        assertEquals(circle.getRadius(), parameters.get("radius"));
        assertEquals(circle.getType(), "CIRCLE");

    }

    @Test
    public void shouldBuildSquare() {
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("side", 4.0);

        CreateShapeCommand createShapeCommand = new CreateShapeCommand("SQUARE", parameters);
        Square square = (Square) shapeBuildService.buildShape(createShapeCommand);

        assertEquals(square.getSide(), parameters.get("side"));
        assertEquals(square.getType(), "SQUARE");

    }

    @Test
    public void shouldBuildRectangle() {
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("width", 5.0);
        parameters.put("height", 6.0);

        CreateShapeCommand createShapeCommand = new CreateShapeCommand("RECTANGLE", parameters);
        Rectangle rectangle = (Rectangle) shapeBuildService.buildShape(createShapeCommand);

        assertEquals(rectangle.getWidth(), parameters.get("width"));
        assertEquals(rectangle.getHeight(), parameters.get("height"));
        assertEquals(rectangle.getType(), "RECTANGLE");

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

    @Test
    public void shouldBuildDtoListFromShapeVew() {
        ShapeView shapeView = new ShapeView(1, "CIRCLE", "test", 0L, LocalDateTime.now(), LocalDateTime.now(), "test", 20.0, 40.0, 5.0, null, null, null);
        ShapeView shapeView2 = new ShapeView(2, "SQUARE", "test", 0L, LocalDateTime.now(), LocalDateTime.now(), "test", 206.0, 405.0, null, 10.0, null, null);
        ShapeView shapeView3 = new ShapeView(3, "RECTANGLE", "test", 0L, LocalDateTime.now(), LocalDateTime.now(), "test", 230.0, 406.0, null, null, 5.0, 10.0);

        List<ShapeView> shapeViewList = new ArrayList<>();

        shapeViewList.add(shapeView);
        shapeViewList.add(shapeView2);
        shapeViewList.add(shapeView3);

        List<ShapeDto> shapeDtoList = shapeBuildService.buildShapeDtoListFromListShapeView(shapeViewList);

        assertEquals(shapeDtoList.get(0).getType(), shapeView.getType());
        assertEquals(shapeDtoList.get(0).getId(), shapeView.getId());
        assertEquals(shapeDtoList.get(1).getId(), shapeView2.getId());
        assertEquals(shapeDtoList.get(1).getType(), shapeView2.getType());
        assertEquals(shapeDtoList.get(2).getId(), shapeView3.getId());
        assertEquals(shapeDtoList.get(2).getType(), shapeView3.getType());
    }
}
