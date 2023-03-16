package com.example.shapesecurity.strategy.builder;

import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.shape.Rectangle;
import com.example.shapesecurity.model.shape.ShapeView;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component("RECTANGLE")
public class RectangleBuilder implements ShapeBuilder {
    @Override
    public Map<String, Object> getNewShape(CreateShapeCommand createShapeCommand) {
        Rectangle rectangle = new Rectangle(createShapeCommand.getParameters().get("width"),
                createShapeCommand.getParameters().get("height"));
        rectangle.setType(Rectangle.class.getSimpleName().toUpperCase(Locale.ROOT));
        ShapeView shapeView = new ShapeView();
        shapeView.setShape(rectangle);
        shapeView.setWidth(rectangle.getWidth());
        shapeView.setHeight(rectangle.getHeight());
        shapeView.setArea(rectangle.computeArea());
        shapeView.setPerimeter(rectangle.computePerimeter());
        shapeView.setType(rectangle.getType());
        shapeView.setCreatedBy(rectangle.getCreatedBy());
        shapeView.setCreatedAt(rectangle.getCreatedAt());
        Map<String, Object> map = new HashMap<>();
        map.put("Shape", rectangle);
        map.put("ShapeView", shapeView);
        return map;
    }
}

