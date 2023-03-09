package com.example.shapesecurity.shapeMapper;

import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.shape.Circle;
import com.example.shapesecurity.model.shape.Rectangle;
import com.example.shapesecurity.model.shape.ShapeView;
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
        ShapeView shapeView = new ShapeView(circle.computeArea(), circle.computePerimeter(),circle);
        parameters.put("radius", 5.0);
        UpdateShapeCommand command = new UpdateShapeCommand( parameters);

        Circle circle2 = (Circle) circleUpdate.updateShape(command, circle,shapeView);

        assertEquals(circle2.getRadius(), command.getParameters().get("radius"));
        assertEquals(circle2.getRadius(), shapeView.getRadius());
        assertEquals(circle2.computeArea(), shapeView.getArea());
        assertEquals(circle2.computePerimeter(), shapeView.getPerimeter());
    }

    @Test
    public void shouldUpdateSquare() {
        Square square = new Square(2.0);
        ShapeView shapeView = new ShapeView(square.computeArea(), square.computePerimeter(),square);
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("side", 5.0);
        UpdateShapeCommand command = new UpdateShapeCommand(parameters);

//        Square square2 = (Square) squareUpdate.updateShape(command, square);
//
//        assertEquals(square2.getSide(), command.getParameters().get("side"));
    }

    @Test
    public void shouldUpdateRectangle() {
        Rectangle rectangle = new Rectangle(2.0, 3.0);
        ShapeView shapeView = new ShapeView(rectangle.computeArea(), rectangle.computePerimeter(),rectangle);
        Map<String, Double> parameters = new HashMap<>();
        parameters.put("width", 5.0);
        parameters.put("height", 6.0);
        UpdateShapeCommand command = new UpdateShapeCommand( parameters);

//        Rectangle rectangle2 = (Rectangle) rectangleUpdate.updateShape(command, rectangle);
//
//        assertEquals(rectangle2.getWidth(), command.getParameters().get("width"));
//        assertEquals(rectangle2.getHeight(), command.getParameters().get("height"));
    }
}
