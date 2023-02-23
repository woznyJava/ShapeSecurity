package com.example.shapesecurity.shapeService;

import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.dto.CircleDto;
import com.example.shapesecurity.model.dto.RectangleDto;
import com.example.shapesecurity.model.dto.SquareDto;
import com.example.shapesecurity.model.shape.Circle;
import com.example.shapesecurity.model.shape.Rectangle;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.model.shape.Square;
import com.example.shapesecurity.repository.ShapeRepository;
import com.example.shapesecurity.service.ShapeBuildService;
import com.example.shapesecurity.service.UpdateService;
import com.example.shapesecurity.service.impl.ShapeServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShapeServiceTests {

    @InjectMocks
    private ShapeServiceImpl shapeService;
    @Mock
    private ShapeRepository shapeRepository;
    @Mock
    private ShapeBuildService shapeBuildService;
    @Mock
    private UpdateService updateService;
    @Captor
    private ArgumentCaptor<Shape> shapeArgumentCaptor;

    @Test
    public void testShouldSaveShape_Circle() {

        Map<String, Double> map = new HashMap<>();
        map.put("radius", 2.0);
        CreateShapeCommand createUpdateShapeCommand = new CreateShapeCommand("CIRCLE", map);
        Circle circle = new Circle(2.0);
        when(shapeBuildService.buildShape(createUpdateShapeCommand)).thenReturn(circle);

        shapeService.save(createUpdateShapeCommand);

        verify(shapeRepository).save(shapeArgumentCaptor.capture());
        Circle circle2 = (Circle) shapeArgumentCaptor.getValue();
        assertEquals(2.0, circle2.getRadius());
        assertEquals(circle2.computeArea(), 12.56);
        assertEquals(circle2.computePerimeter(), 12.56);
    }

    @Test
    public void testShouldSaveShape_Square() {

        Map<String, Double> map = new HashMap<>();
        map.put("side", 3.0);
        CreateShapeCommand createUpdateShapeCommand = new CreateShapeCommand("SQUARE", map);
        Square square = new Square(3.0);
        when(shapeBuildService.buildShape(createUpdateShapeCommand)).thenReturn(square);

        shapeService.save(createUpdateShapeCommand);

        verify(shapeRepository).save(shapeArgumentCaptor.capture());
        Square square2 = (Square) shapeArgumentCaptor.getValue();
        assertEquals(3.0, square2.getSide());
        assertEquals(square2.computeArea(), 9.0);
        assertEquals(square2.computePerimeter(), 12.0);
    }

    @Test
    public void testShouldSaveShape_Rectangle() {

        Map<String, Double> map = new HashMap<>();
        map.put("width", 4.0);
        map.put("height", 5.0);

        CreateShapeCommand createUpdateShapeCommand = new CreateShapeCommand("RECTANGLE", map);
        Rectangle rectangle = new Rectangle(4.0, 5.0);
        when(shapeBuildService.buildShape(createUpdateShapeCommand)).thenReturn(rectangle);
        shapeService.save(createUpdateShapeCommand);

        verify(shapeRepository).save(shapeArgumentCaptor.capture());
        Rectangle rectangle2 = (Rectangle) shapeArgumentCaptor.getValue();
        assertEquals(rectangle.getWidth(), rectangle2.getWidth());
        assertEquals(rectangle.getHeight(), rectangle2.getHeight());
        assertEquals(rectangle2.computeArea(), 20.0);
        assertEquals(rectangle2.computePerimeter(), 18.0);
    }

    @Test
    public void testUpdateShapeCircle() {
        Circle circle = new Circle(2.0);
        circle.setId(1);
        CircleDto circleDto = new CircleDto(4.0);
        Map<String, Double> map = new HashMap<>();
        map.put("radius", 4.0);
        UpdateShapeCommand updateShapeCommand = new UpdateShapeCommand(1, map);

        when(updateService.update(updateShapeCommand)).thenReturn(circle);
        when(shapeBuildService.buildShapeDto(circle)).thenReturn(circleDto);

        CircleDto circleDto2 = (CircleDto) shapeService.update(updateShapeCommand);

        assertEquals(circleDto.getRadius(), circleDto2.getRadius());
    }

    @Test
    public void testUpdateShapeSquare() {
        Square square = new Square(2.0);
        square.setId(1);
        SquareDto squareDto = new SquareDto(4.0);
        Map<String, Double> map = new HashMap<>();
        map.put("side", 4.0);
        UpdateShapeCommand updateShapeCommand = new UpdateShapeCommand(1, map);

        when(updateService.update(updateShapeCommand)).thenReturn(square);
        when(shapeBuildService.buildShapeDto(square)).thenReturn(squareDto);

        SquareDto squareDto2 = (SquareDto) shapeService.update(updateShapeCommand);

        assertEquals(squareDto.getSide(), squareDto2.getSide());
    }

    @Test
    public void testUpdateShapeRectangle() {
        Rectangle rectangle = new Rectangle(2.0, 5.0);
        rectangle.setId(1);
        RectangleDto rectangleDto = new RectangleDto(6.0, 9.0);
        Map<String, Double> map = new HashMap<>();
        map.put("width", 6.0);
        map.put("height", 9.0);
        UpdateShapeCommand updateShapeCommand = new UpdateShapeCommand(1, map);

        when(updateService.update(updateShapeCommand)).thenReturn(rectangle);
        when(shapeBuildService.buildShapeDto(rectangle)).thenReturn(rectangleDto);

        RectangleDto rectangleDto2 = (RectangleDto) shapeService.update(updateShapeCommand);

        assertEquals(rectangleDto.getHeight(), rectangleDto2.getHeight());
        assertEquals(rectangleDto.getWidth(), rectangleDto2.getWidth());
    }
}
