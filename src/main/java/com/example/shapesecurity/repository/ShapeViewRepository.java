package com.example.shapesecurity.repository;

import com.example.shapesecurity.model.shape.ShapeView;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ShapeViewRepository extends PagingAndSortingRepository<ShapeView, Integer>, JpaSpecificationExecutor<ShapeView> {
    List<ShapeView> findAll();
}
