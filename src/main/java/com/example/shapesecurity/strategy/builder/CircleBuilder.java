package com.example.shapesecurity.strategy.builder;

import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.shape.Circle;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component("CIRCLE")
public class CircleBuilder implements ShapeBuilder {
    @Override
    public Map<String, Object> getNewShape(CreateShapeCommand createShapeCommand) {
        Circle circle = new Circle(createShapeCommand.getParameters().get("radius"));
        circle.setType(Circle.class.getSimpleName().toUpperCase(Locale.ROOT));
//        ShapeView shapeView = new ShapeView(circle);

//        shapeView.setRadius(circle.getRadius());
//        shapeView.setArea(circle.computeArea());
//        shapeView.setPerimeter(circle.computePerimeter());
//        shapeView.setType(circle.getType());
//        shapeView.setCreatedBy(circle.getCreatedBy());
//        shapeView.setCreatedAt(circle.getCreatedAt());
        Map<String, Object> map = new HashMap<>();
        map.put("Shape",circle);
//        map.put("ShapeView", shapeView);
        return map;
    }
}