package com.example.shapesecurity.strategy.builder;

import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.model.shape.Square;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component("SQUARE")
public class SquareBuilder implements ShapeBuilder {
    @Override
    public Shape getNewShape(CreateShapeCommand createShapeCommand) {
        Square square = new Square(createShapeCommand.getParameters().get("side"));
        square.setType(Square.class.getSimpleName().toUpperCase(Locale.ROOT));
        return square;
    }
}