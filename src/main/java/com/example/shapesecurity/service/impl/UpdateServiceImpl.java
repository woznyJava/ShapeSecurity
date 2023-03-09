package com.example.shapesecurity.service.impl;

import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.model.shape.ShapeView;
import com.example.shapesecurity.repository.ShapeRepository;
import com.example.shapesecurity.service.ShapeViewService;
import com.example.shapesecurity.service.UpdateService;
import com.example.shapesecurity.strategy.updateMapper.ShapeUpdate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Locale;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UpdateServiceImpl implements UpdateService {
    private static final String UPDATE = "UPDATE";
    private final ShapeRepository shapeRepository;
    private final ShapeViewService shapeViewService;
    private final Map<String, ShapeUpdate> shapeUpdate;

    @Override
    @Transactional
    public Shape update(UpdateShapeCommand updateShapeCommand, int id) {
        Shape shape = shapeRepository.findWithLockingById(id)
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Shape with id=%s has not been found", id)));
       ShapeView shapeView = shape.getShapeView();
        String strategyName = shape.getType().toUpperCase(Locale.ROOT) + UPDATE;
         Map<String, Object> map = shapeUpdate.get(strategyName)
                .updateShape(updateShapeCommand, shape, shapeView);
        shapeViewService.save((ShapeView) map.get("ShapeView"));
         return (Shape)map.get("Shape");
    }
}
