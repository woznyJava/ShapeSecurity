package com.example.shapesecurity.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShapeDto {
    private int id;
    private String type;
    private Long version;
    private String createdBy;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private String createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private String lastModifiedAt;
    private String lastModifiedBy;
    private Double area;
    private Double perimeter;

}
