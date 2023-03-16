package com.example.shapesecurity.strategy.builder;

import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.shape.ShapeView;
import com.example.shapesecurity.model.shape.Square;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Component("SQUARE")
public class SquareBuilder implements ShapeBuilder {
    @Override
    public Map<String, Object> getNewShape(CreateShapeCommand createShapeCommand) {
        Square square = new Square(createShapeCommand.getParameters().get("side"));
        square.setType(Square.class.getSimpleName().toUpperCase(Locale.ROOT));
        ShapeView shapeView = new ShapeView();
        shapeView.setShape(square);
        shapeView.setSide(square.getSide());
        shapeView.setArea(square.computeArea());
        shapeView.setPerimeter(square.computePerimeter());
        shapeView.setType(square.getType());
        shapeView.setCreatedBy(square.getCreatedBy());
        shapeView.setCreatedAt(square.getCreatedAt());
        Map<String, Object> map = new HashMap<>();
        map.put("Shape", square);
        map.put("ShapeView", shapeView);
        return map;
    }
}

