package com.example.shapesecurity.model.shape;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Immutable
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShapeView {
    @Id
    private int id;
    private String type;
    private String createdBy;
    private Long version;
    private LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private String lastModifiedBy;
    private double area;
    private double perimeter;
    private Double radius;
    private Double side;
    private Double height;
    private Double width;

}