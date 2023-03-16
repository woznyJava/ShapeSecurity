package com.example.shapesecurity.shapeService;

import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.shape.*;
import com.example.shapesecurity.repository.ShapeRepository;
import com.example.shapesecurity.service.ShapeBuildService;
import com.example.shapesecurity.service.ShapeViewService;
import com.example.shapesecurity.service.impl.ShapeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

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
    private ShapeViewService shapeViewService;

    @Captor
    private ArgumentCaptor<Shape> shapeArgumentCaptor;
    @Mock
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        authentication = Mockito.mock(Authentication.class);
    }

    @Test
    public void testShouldSaveShape_Circle() {

        Map<String, Double> map = new HashMap<>();
        map.put("radius", 2.0);
        CreateShapeCommand createUpdateShapeCommand = new CreateShapeCommand("CIRCLE", map);
        Circle circle = new Circle(2.0);
        ShapeView shapeView = new ShapeView(circle.computeArea(), circle.computePerimeter(), circle);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("Shape", circle);
        map2.put("ShapeView", shapeView);
        when(shapeBuildService.buildShape(createUpdateShapeCommand)).thenReturn(map2);

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
        ShapeView shapeView = new ShapeView(square.computeArea(), square.computePerimeter(), square);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("Shape", square);
        map2.put("ShapeView", shapeView);
        when(shapeBuildService.buildShape(createUpdateShapeCommand)).thenReturn(map2);
        when(shapeBuildService.buildShape(createUpdateShapeCommand)).thenReturn(map2);

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
        ShapeView shapeView = new ShapeView(rectangle.computeArea(), rectangle.computePerimeter(), rectangle);
        Map<String, Object> map2 = new HashMap<>();
        map2.put("Shape", rectangle);
        map2.put("ShapeView", shapeView);
        when(shapeBuildService.buildShape(createUpdateShapeCommand)).thenReturn(map2);
        shapeService.save(createUpdateShapeCommand);

        verify(shapeRepository).save(shapeArgumentCaptor.capture());
        Rectangle rectangle2 = (Rectangle) shapeArgumentCaptor.getValue();
        assertEquals(rectangle.getWidth(), rectangle2.getWidth());
        assertEquals(rectangle.getHeight(), rectangle2.getHeight());
        assertEquals(rectangle2.computeArea(), 20.0);
        assertEquals(rectangle2.computePerimeter(), 18.0);
    }
}
