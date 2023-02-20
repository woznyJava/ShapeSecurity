package com.example.shapesecurity.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FilterRequest {
    private String createdBy;
    private String shapeType;
    private Double areaFrom;
    private Double areaTo;
    private Double perimeterFrom;
    private Double perimeterTo;
    private LocalDateTime createdAtFrom;
    private LocalDateTime createdAtTo;
    private Long version;
    private Double sideFrom;
    private Double sideTo;
    private Double widthFrom;
    private Double widthTo;
    private Double heightFrom;
    private Double heightTo;
    private Double radiusFrom;
    private Double radiusTo;
}