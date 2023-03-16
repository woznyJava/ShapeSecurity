package com.example.shapesecurity.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShapeDto {
    private int id;
    private String type;
    private Long version;
    private String createdBy;
    private String createdAt;
    private String lastModifiedAt;
    private String lastModifiedBy;
    private Double area;
    private Double perimeter;
}
