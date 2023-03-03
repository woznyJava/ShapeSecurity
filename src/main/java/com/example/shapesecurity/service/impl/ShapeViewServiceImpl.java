package com.example.shapesecurity.service.impl;

import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.model.shape.ShapeView;
import com.example.shapesecurity.repository.ShapeViewRepository;
import com.example.shapesecurity.service.ShapeViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShapeViewServiceImpl implements ShapeViewService {
    private final ShapeViewRepository shapeViewRepository;

    @Override
    public void save(ShapeView shapeView) {
        shapeViewRepository.save(shapeView);
    }

    @Override
    public ShapeView create(Shape shape) {
        return new ShapeView(shape.computeArea(), shape.computePerimeter(), shape);
    }
}
