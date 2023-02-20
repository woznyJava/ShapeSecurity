package com.example.shapesecurity.strategy.updateMapper;//package com.example.shapesspringsecurity.strategy.updateMapper;

import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.shape.Circle;
import com.example.shapesecurity.model.shape.Shape;
import org.springframework.stereotype.Component;

@Component("CIRCLEUPDATE")
public class CircleUpdate implements ShapeUpdate {
    @Override
    public Shape updateShape(UpdateShapeCommand updateShapeCommand, Shape shape) {
        Circle circle = (Circle) shape;
        circle.setRadius(updateShapeCommand.getParameters().get("radius"));
        return circle;
    }
}
