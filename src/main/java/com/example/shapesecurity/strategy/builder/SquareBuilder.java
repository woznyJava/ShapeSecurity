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
//        shapeView.setShape(square);
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

//CREATE TABLE SHAPE_VIEW
//(
//    id               INTEGER AUTO_INCREMENT NOT NULL UNIQUE,
//    area             DOUBLE PRECISION,
//    created_at       TIMESTAMP,
//    created_by       VARCHAR(255),
//    height           DOUBLE PRECISION,
//    last_modified_at TIMESTAMP,
//    perimeter        DOUBLE PRECISION,
//    radius           DOUBLE PRECISION,
//    side             DOUBLE PRECISION,
//    type             VARCHAR(255),
//    version          BIGINT,
//    width            DOUBLE PRECISION,
//    shape_id         INTEGER,
//    PRIMARY KEY (id)
//);
//
//

//    CREATE VIEW SHAPE_VIEW AS
//    SELECT
//            dtype,
//            id,
//            created_by,
//            type,
//            version,
//            created_at,
//            last_modified_at,
//            last_modified_by,
//            radius,
//            height,
//            width,
//            side,
//            area,
//            perimeter
//    FROM APP_SHAPE;