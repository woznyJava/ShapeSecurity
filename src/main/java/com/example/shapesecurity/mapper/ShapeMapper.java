package com.example.shapesecurity.mapper;

import com.example.shapesecurity.model.dto.CircleDto;
import com.example.shapesecurity.model.dto.RectangleDto;
import com.example.shapesecurity.model.dto.SquareDto;
import com.example.shapesecurity.model.shape.Circle;
import com.example.shapesecurity.model.shape.Rectangle;
import com.example.shapesecurity.model.shape.Square;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface ShapeMapper {
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "dd.MM.yyyy 'T' HH:MM")
    RectangleDto fromRectangle(Rectangle rectangle);

    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "dd.MM.yyyy 'T' HH:MM")
    CircleDto fromCircle(Circle circle);

    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "dd.MM.yyyy 'T' HH:MM")
    SquareDto fromSquare(Square square);
}
