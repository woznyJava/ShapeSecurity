package com.example.shapesecurity.strategy.updateMapper;//package com.example.shapesspringsecurity.strategy.updateMapper;

import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.shape.Circle;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.model.shape.ShapeView;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("CIRCLEUPDATE")
public class CircleUpdate implements ShapeUpdate {
    @Override
    public Map<String, Object> updateShape(UpdateShapeCommand updateShapeCommand, Shape shape, ShapeView shapeView) {
        Circle circle = (Circle) shape;
        circle.setRadius(updateShapeCommand.getParameters().get("radius"));
        shapeView.setRadius(updateShapeCommand.getParameters().get("radius"));
        shapeView.setArea(circle.computeArea());
        shapeView.setPerimeter(circle.computePerimeter());
        Map<String, Object> map = new HashMap<>();
        map.put("Shape", circle);
        map.put("ShapeView", shapeView);
        return map;
    }
}
