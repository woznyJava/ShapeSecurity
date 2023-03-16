package com.example.shapesecurity.shapeMapper;


import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.shape.Circle;
import com.example.shapesecurity.model.shape.Rectangle;
import com.example.shapesecurity.model.shape.ShapeView;
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
    public void shouldReturnCircleAndShapeView() {
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("radius", 7.0);
        CreateShapeCommand command = new CreateShapeCommand("CIRCLE", parameters);

        Map<String, Object> map = circleBuilder.getNewShape(command);
        Circle circle = (Circle) map.get("Shape");
        ShapeView shapeView = (ShapeView) map.get("ShapeView");

        assertEquals(circle.getRadius(), command.getParameters().get("radius"));
        assertEquals(circle.computeArea(), 153.86);
        assertEquals(circle.computePerimeter(), 43.96);
        assertEquals(circle.getType(), command.getType());
        assertEquals(shapeView.getShape(), circle);
        assertEquals(shapeView.getArea(), circle.computeArea());
        assertEquals(shapeView.getPerimeter(), circle.computePerimeter());
        assertEquals(shapeView.getRadius(), circle.getRadius());


    }

    @Test
    public void shouldReturnSquareAndShapeView() {
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("side", 5.0);
        CreateShapeCommand command = new CreateShapeCommand("SQUARE", parameters);

        Map<String, Object> map = squareBuilder.getNewShape(command);
        ShapeView shapeView = (ShapeView) map.get("ShapeView");
        Square square = (Square) map.get("Shape");

        assertEquals(square.getSide(), command.getParameters().get("side"));
        assertEquals(square.getType(), command.getType());
        assertEquals(square, shapeView.getShape());
        assertEquals(square.computeArea(), shapeView.getArea());
        assertEquals(square.computePerimeter(), shapeView.getPerimeter());
        assertEquals(square.getSide(), shapeView.getSide());
    }

    @Test
    public void shouldReturnRectangleAndShapeView() {
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("width", 5.0);
        parameters.put("height", 10.0);

        CreateShapeCommand command = new CreateShapeCommand("RECTANGLE", parameters);

        Map<String, Object> map = rectangleBuilder.getNewShape(command);
        ShapeView shapeView = (ShapeView) map.get("ShapeView");
        Rectangle rectangle = (Rectangle) map.get("Shape");

        assertEquals(rectangle.getHeight(), command.getParameters().get("height"));
        assertEquals(rectangle.getWidth(), command.getParameters().get("width"));
        assertEquals(rectangle.getType(), command.getType());
        assertEquals(rectangle, shapeView.getShape());
        assertEquals(rectangle.getWidth(), shapeView.getWidth());
        assertEquals(rectangle.getHeight(), shapeView.getHeight());
        assertEquals(rectangle.computePerimeter(), shapeView.getPerimeter());
        assertEquals(rectangle.computeArea(), shapeView.getArea());
    }
}


