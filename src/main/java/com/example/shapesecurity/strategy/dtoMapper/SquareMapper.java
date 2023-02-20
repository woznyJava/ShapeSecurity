package com.example.shapesecurity.strategy.dtoMapper;

import com.example.shapesecurity.mapper.ShapeMapper;
import com.example.shapesecurity.model.dto.ShapeDto;
import com.example.shapesecurity.model.dto.SquareDto;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.model.shape.Square;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("SQUAREDTO")
public class SquareMapper implements ShapeDtoMapper {
    @Autowired
    private ShapeMapper shapeMapper;
    @Override
    public ShapeDto mapShapeToShapeDto(Shape shape) {
        Square square = (Square) shape;
        SquareDto squareDto = shapeMapper.fromSquare(square);
        squareDto.setArea(square.computeArea());
        squareDto.setPerimeter(square.computePerimeter());
        return squareDto;
    }
}
