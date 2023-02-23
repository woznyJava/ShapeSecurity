package com.example.shapesecurity.shapeMapper;

import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.shape.Circle;
import com.example.shapesecurity.model.shape.Rectangle;
import com.example.shapesecurity.model.shape.Square;
import com.example.shapesecurity.strategy.updateMapper.CircleUpdate;
import com.example.shapesecurity.strategy.updateMapper.RectangleUpdate;
import com.example.shapesecurity.strategy.updateMapper.SquareUpdate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ShapeUpdateTests {
    @InjectMocks
    private CircleUpdate circleUpdate;

    @InjectMocks
    private SquareUpdate squareUpdate;

    @InjectMocks
    private RectangleUpdate rectangleUpdate;

    @Test
    public void shouldUpdateCircle() {
        Circle circle = new Circle(2.0);
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("radius", 5.0);
        UpdateShapeCommand command = new UpdateShapeCommand(1, parameters);

        Circle circle2 = (Circle) circleUpdate.updateShape(command, circle);

        assertEquals(circle2.getRadius(), command.getParameters().get("radius"));
    }

    @Test
    public void shouldUpdateSquare() {
        Square square = new Square(2.0);
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("side", 5.0);
        UpdateShapeCommand command = new UpdateShapeCommand(1, parameters);

        Square square2 = (Square) squareUpdate.updateShape(command, square);

        assertEquals(square2.getSide(), command.getParameters().get("side"));
    }

    @Test
    public void shouldUpdateRectangle() {
        Rectangle rectangle = new Rectangle(2.0, 3.0);
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("width", 5.0);
        parameters.put("height", 6.0);
        UpdateShapeCommand command = new UpdateShapeCommand(1, parameters);

        Rectangle rectangle2 = (Rectangle) rectangleUpdate.updateShape(command, rectangle);

        assertEquals(rectangle2.getWidth(), command.getParameters().get("width"));
        assertEquals(rectangle2.getHeight(), command.getParameters().get("height"));
    }
}
