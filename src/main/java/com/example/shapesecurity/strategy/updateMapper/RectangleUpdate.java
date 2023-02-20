package com.example.shapesecurity.strategy.updateMapper;

import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.shape.Rectangle;
import com.example.shapesecurity.model.shape.Shape;
import org.springframework.stereotype.Component;

@Component("RECTANGLEUPDATE")
public class RectangleUpdate implements ShapeUpdate {
    @Override
    public Shape updateShape(UpdateShapeCommand updateShapeCommand, Shape shape) {
        Rectangle rectangle = (Rectangle) shape;
        rectangle.setHeight(updateShapeCommand.getParameters().get("height"));
        rectangle.setWidth(updateShapeCommand.getParameters().get("width"));
        return rectangle;
    }
}
