package com.example.shapesecurity.repository;

import com.example.shapesecurity.model.shape.Shape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShapeRepository extends JpaRepository<Shape, Integer>, JpaSpecificationExecutor<Shape> {
}
