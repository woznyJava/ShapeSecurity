package com.example.shapesecurity.strategy.builder;

import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.shape.Circle;
import com.example.shapesecurity.model.shape.ShapeView;
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
        ShapeView shapeView = new ShapeView();
        shapeView.setShape(circle);
        shapeView.setRadius(circle.getRadius());
        shapeView.setArea(circle.computeArea());
        shapeView.setPerimeter(circle.computePerimeter());
        Map<String, Object> map = new HashMap<>();
        map.put("Shape",circle);
        map.put("ShapeView", shapeView);
        return map;
    }
}
// robie mape zamaist shape i wrzucam shape view i shape