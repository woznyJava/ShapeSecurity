package com.example.shapesecurity.strategy.updateMapper;

import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.model.shape.ShapeView;
import com.example.shapesecurity.model.shape.Square;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component("SQUAREUPDATE")
public class SquareUpdate implements ShapeUpdate {
    @Override
    public Map<String, Object> updateShape(UpdateShapeCommand updateShapeCommand, Shape shape, ShapeView shapeView) {
        Square square = (Square) shape;
        square.setSide(updateShapeCommand.getParameters().get("side"));
        shapeView.setArea(square.computeArea());
        shapeView.setPerimeter(square.computePerimeter());
        shapeView.setSide(updateShapeCommand.getParameters().get("side"));
        Map<String, Object> map = new HashMap<>();
        map.put("Shape", square);
        map.put("ShapeView", shapeView);
        return map;
    }
}
