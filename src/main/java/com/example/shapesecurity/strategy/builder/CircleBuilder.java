package com.example.shapesecurity.strategy.builder;

import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.shape.Circle;
import com.example.shapesecurity.model.shape.Shape;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component("CIRCLE")
public class CircleBuilder implements ShapeBuilder {
    @Override
    public Shape getNewShape(CreateShapeCommand createShapeCommand) {
        Circle circle = new Circle(createShapeCommand.getParameters().get("radius"));
        circle.setType(Circle.class.getSimpleName().toUpperCase(Locale.ROOT));
        return circle;
    }
}
