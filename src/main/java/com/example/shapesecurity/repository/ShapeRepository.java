package com.example.shapesecurity.repository;

import com.example.shapesecurity.model.shape.Shape;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.LockModeType;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;



public interface ShapeRepository extends JpaRepository<Shape, Integer> {
    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "CREATE VIEW shape_view AS" +
            " SELECT id, dtype, type, created_by, created_at, last_modified_at, last_modified_by, radius, width, height, side, version, CASE " +
            "WHEN type = 'CIRCLE' THEN 3.14 * radius * radius " +
            "WHEN type = 'RECTANGLE' THEN width * height " +
            "WHEN type = 'SQUARE' THEN side * side " +
            "ELSE NULL " +
            "END AS area, " +
            "CASE " +
            "WHEN type = 'CIRCLE' THEN 2 * 3.14 * radius " +
            "WHEN type = 'RECTANGLE' THEN 2 * (width + height) " +
            "WHEN type = 'SQUARE' THEN 4 * side " +
            "ELSE NULL " +
            "END AS perimeter " +
            "FROM APP_SHAPE WHERE id IS NOT NULL")
    void createView();
    @Query(nativeQuery = true, value = "SELECT * FROM shape_view " +
            "WHERE (:createdBy IS NULL OR created_by = :createdBy) " +
            "AND (:shapeType IS NULL OR type = :shapeType) " +
            "AND (:areaFrom IS NULL OR area >= :areaFrom) " +
            "AND (:areaTo IS NULL OR area <= :areaTo) " +
            "AND (:perimeterFrom IS NULL OR perimeter >= :perimeterFrom) " +
            "AND (:perimeterTo IS NULL OR perimeter <= :perimeterTo) " +
            "AND (:createdAtFrom IS NULL OR created_at >= :createdAtFrom) " +
            "AND (:createdAtTo IS NULL OR created_at <= :createdAtTo) " +
            "AND (:version IS NULL OR version = :version) " +
            "AND (:sideFrom IS NULL OR side >= :sideFrom) " +
            "AND (:sideTo IS NULL OR side <= :sideTo) " +
            "AND (:widthFrom IS NULL OR width >= :widthFrom) " +
            "AND (:widthTo IS NULL OR width <= :widthTo) " +
            "AND (:heightFrom IS NULL OR height >= :heightFrom) " +
            "AND (:heightTo IS NULL OR height <= :heightTo) " +
            "AND (:radiusFrom IS NULL OR radius >= :radiusFrom) " +
            "AND (:radiusTo IS NULL OR radius <= :radiusTo)")
    List<Shape> findShapes(@Param("createdBy") String createdBy,
                           @Param("shapeType") String shapeType,
                           @Param("areaFrom") Double areaFrom,
                           @Param("areaTo") Double areaTo,
                           @Param("perimeterFrom") Double perimeterFrom,
                           @Param("perimeterTo") Double perimeterTo,
                           @Param("createdAtFrom") LocalDateTime createdAtFrom,
                           @Param("createdAtTo") LocalDateTime createdAtTo,
                           @Param("version") Long version,
                           @Param("sideFrom") Double sideFrom,
                           @Param("sideTo") Double sideTo,
                           @Param("widthFrom") Double widthFrom,
                           @Param("widthTo") Double widthTo,
                           @Param("heightFrom") Double heightFrom,
                           @Param("heightTo") Double heightTo,
                           @Param("radiusFrom") Double radiusFrom,
                           @Param("radiusTo") Double radiusTo);
    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<Shape> findWithLockingById(int id);

}
