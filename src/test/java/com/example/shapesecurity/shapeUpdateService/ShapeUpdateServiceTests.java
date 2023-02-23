package com.example.shapesecurity.shapeUpdateService;

import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.shape.Circle;
import com.example.shapesecurity.model.shape.Rectangle;
import com.example.shapesecurity.model.shape.Square;
import com.example.shapesecurity.repository.ShapeRepository;
import com.example.shapesecurity.service.impl.UpdateServiceImpl;
import com.example.shapesecurity.strategy.updateMapper.CircleUpdate;
import com.example.shapesecurity.strategy.updateMapper.RectangleUpdate;
import com.example.shapesecurity.strategy.updateMapper.ShapeUpdate;
import com.example.shapesecurity.strategy.updateMapper.SquareUpdate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ShapeUpdateServiceTests {
    @Mock
    private ShapeRepository shapeRepository;

    @Spy
    private Map<String, ShapeUpdate> shapeUpdateMap = new HashMap<>();

    @InjectMocks
    private UpdateServiceImpl updateService;

    @BeforeEach
    public void setUp() {
        shapeUpdateMap.put("CIRCLEUPDATE", new CircleUpdate());
        shapeUpdateMap.put("RECTANGLEUPDATE", new RectangleUpdate());
        shapeUpdateMap.put("SQUAREUPDATE", new SquareUpdate());
    }


    @Test
    public void testShouldUpdateShape_Circle() {
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("radius", 5.0);
        Circle circle = new Circle(2.0);
        circle.setType("CIRCLE");
        UpdateShapeCommand updateShapeCommand = new UpdateShapeCommand(1, parameters);

        when(shapeRepository.findWithLockingById(anyInt())).thenReturn(Optional.of(circle));

        Circle circleUpdated = (Circle) updateService.update(updateShapeCommand);

        assertEquals(circleUpdated.getRadius(), parameters.get("radius"));
    }

    @Test
    public void testShouldUpdateShape_Square() {
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("side", 5.0);
        Square square = new Square(2.0);
        square.setType("SQUARE");
        UpdateShapeCommand updateShapeCommand = new UpdateShapeCommand(1, parameters);

        when(shapeRepository.findWithLockingById(anyInt())).thenReturn(Optional.of(square));

        Square squareUpdated = (Square) updateService.update(updateShapeCommand);

        assertEquals(squareUpdated.getSide(), parameters.get("side"));
    }

    @Test
    public void testShouldUpdateShape_Rectangle() {
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("radius", 5.0);
        parameters.put("height", 5.0);
        Rectangle rectangle = new Rectangle(5.0, 5.0);
        rectangle.setType("RECTANGLE");
        UpdateShapeCommand updateShapeCommand = new UpdateShapeCommand(1, parameters);

        when(shapeRepository.findWithLockingById(anyInt())).thenReturn(Optional.of(rectangle));

        Rectangle rectangleUpdated = (Rectangle) updateService.update(updateShapeCommand);

        assertEquals(rectangleUpdated.getHeight(), parameters.get("height"));
        assertEquals(rectangleUpdated.getWidth(), parameters.get("width"));
    }
}
