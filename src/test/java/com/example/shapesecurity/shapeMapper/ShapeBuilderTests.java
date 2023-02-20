package com.example.shapesecurity.shapeMapper;


import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.shape.Circle;
import com.example.shapesecurity.model.shape.Rectangle;
import com.example.shapesecurity.model.shape.Square;
import com.example.shapesecurity.strategy.builder.CircleBuilder;
import com.example.shapesecurity.strategy.builder.RectangleBuilder;
import com.example.shapesecurity.strategy.builder.SquareBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ShapeBuilderTests {
    @InjectMocks
    private CircleBuilder circleBuilder;
    @InjectMocks
    private RectangleBuilder rectangleBuilder;
    @InjectMocks
    private SquareBuilder squareBuilder;

    @Test
    public void shouldReturnCircle() {
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("radius", 5.0);
        CreateShapeCommand command = new CreateShapeCommand("CIRCLE", parameters);

        Circle circle = (Circle) circleBuilder.getNewShape(command);

        assertEquals(circle.getRadius(), command.getParameters().get("radius"));
        assertEquals(circle.getType(), command.getType());

    }

    @Test
    public void shouldReturnSquare() {
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("side", 5.0);
        CreateShapeCommand command = new CreateShapeCommand("SQUARE", parameters);

        Square square = (Square) squareBuilder.getNewShape(command);

        assertEquals(square.getSide(), command.getParameters().get("side"));
        assertEquals(square.getType(), command.getType());
    }

    @Test
    public void shouldReturnRectangle() {
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("width", 5.0);
        parameters.put("height", 10.0);

        CreateShapeCommand command = new CreateShapeCommand("RECTANGLE", parameters);

        Rectangle rectangle = (Rectangle) rectangleBuilder.getNewShape(command);

        assertEquals(rectangle.getHeight(), command.getParameters().get("height"));
        assertEquals(rectangle.getWidth(), command.getParameters().get("width"));
        assertEquals(rectangle.getType(), command.getType());
    }
}


