package com.example.shapesecurity.mapper;

import com.example.shapesecurity.model.dto.CircleDto;
import com.example.shapesecurity.model.dto.RectangleDto;
import com.example.shapesecurity.model.dto.SquareDto;
import com.example.shapesecurity.model.shape.Circle;
import com.example.shapesecurity.model.shape.Rectangle;
import com.example.shapesecurity.model.shape.Square;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ShapeMapper {
    @Mapping(source = "lastModifiedAt", target = "lastModifiedAt", dateFormat = "dd.MM.yyyy 'T' HH:MM")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "dd.MM.yyyy 'T' HH:MM")
    RectangleDto fromRectangle(Rectangle rectangle);

    @Mapping(source = "lastModifiedAt", target = "lastModifiedAt", dateFormat = "dd.MM.yyyy 'T' HH:MM")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "dd.MM.yyyy 'T' HH:MM")
    CircleDto fromCircle(Circle circle);

    @Mapping(source = "lastModifiedAt", target = "lastModifiedAt", dateFormat = "dd.MM.yyyy 'T' HH:MM")
    @Mapping(source = "createdAt", target = "createdAt", dateFormat = "dd.MM.yyyy 'T' HH:MM")
    SquareDto fromSquare(Square square);
}
