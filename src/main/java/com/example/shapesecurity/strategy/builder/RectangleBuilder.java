package com.example.shapesecurity.strategy.builder;

import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.shape.Rectangle;
import com.example.shapesecurity.model.shape.Shape;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component("RECTANGLE")
public class RectangleBuilder implements ShapeBuilder {
    @Override
    public Shape getNewShape(CreateShapeCommand createShapeCommand) {
        Rectangle rectangle = new Rectangle(createShapeCommand.getParameters().get("width"),
                createShapeCommand.getParameters().get("height"));
        rectangle.setType(Rectangle.class.getSimpleName().toUpperCase(Locale.ROOT));
        return rectangle;
    }
}


