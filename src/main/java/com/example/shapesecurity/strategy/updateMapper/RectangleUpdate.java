package com.example.shapesecurity.strategy.updateMapper;

import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.shape.Rectangle;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.model.shape.ShapeView;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("RECTANGLEUPDATE")
public class RectangleUpdate implements ShapeUpdate {
    @Override
    public Map<String, Object> updateShape(UpdateShapeCommand updateShapeCommand, Shape shape, ShapeView shapeView) {
        Rectangle rectangle = (Rectangle) shape;
        rectangle.setHeight(updateShapeCommand.getParameters().get("height"));
        rectangle.setWidth(updateShapeCommand.getParameters().get("width"));
        shapeView.setArea(rectangle.computeArea());
        shapeView.setPerimeter(rectangle.computePerimeter());
        shapeView.setWidth(updateShapeCommand.getParameters().get("width"));
        shapeView.setHeight(updateShapeCommand.getParameters().get("height"));
        Map<String, Object> map = new HashMap<>();
        map.put("Shape", rectangle);
        map.put("ShapeView", shapeView);
        return map;
    }
}
