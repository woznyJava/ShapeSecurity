package com.example.shapesecurity.service.impl;

import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.repository.ShapeRepository;
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
    private final Map<String, ShapeUpdate> shapeUpdate;

    @Override
    @Transactional
    public Shape update(UpdateShapeCommand updateShapeCommand) {
        Shape shape = shapeRepository.findWithLockingById(updateShapeCommand.getId())
                .orElseThrow(() -> new EntityNotFoundException(String
                        .format("Shape with id=%s has not been found", updateShapeCommand.getId())));
        String strategyName = shape.getType().toUpperCase(Locale.ROOT) + UPDATE;
        return shapeUpdate.get(strategyName)
                .updateShape(updateShapeCommand, shape);
    }
}
