package com.example.shapesecurity.service;


import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.shape.Shape;

public interface UpdateService {

    Shape update(UpdateShapeCommand updateShapeCommand);

    Shape getShapeOptimisticLock(int id);
}
