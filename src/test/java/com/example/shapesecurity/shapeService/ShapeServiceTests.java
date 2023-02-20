package com.example.shapesecurity.shapeService;

import com.example.shapesecurity.model.command.CreateShapeCommand;
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
    public void testUpdateShapeCircle(){
        Circle circle = new Circle(2.0);



    }


}
