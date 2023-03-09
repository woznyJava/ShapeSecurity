package com.example.shapesecurity.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
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
//@Builder
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
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

//    public ShapeDto(NumberPath<Integer> id, String toString, NumberPath<Long> version, StringPath createdBy, String toString1, String toString2, String toString3) {
//    }
}
