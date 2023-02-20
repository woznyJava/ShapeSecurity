package com.example.shapesecurity.strategy.updateMapper;

import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.model.shape.Square;
import org.springframework.stereotype.Component;

@Component("SQUAREUPDATE")
public class SquareUpdate implements ShapeUpdate {
    @Override
    public Shape updateShape(UpdateShapeCommand updateShapeCommand, Shape shape) {
        Square square = (Square) shape;
        square.setSide(updateShapeCommand.getParameters().get("side"));
        return square;
    }
}
