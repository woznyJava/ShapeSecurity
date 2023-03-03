package com.example.shapesecurity.model.shape;

import com.example.shapesecurity.model.ShapeEntityListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "APP_SHAPE")
@Entity
//@EntityListeners(AuditingEntityListener.class)
@EntityListeners(ShapeEntityListener.class)

public abstract class Shape {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String type;
    @CreatedBy
    private String createdBy;
    @Version
    private Long version;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;
    @LastModifiedBy
    private String lastModifiedBy;
    @OneToOne(mappedBy = "shape", cascade = CascadeType.ALL, orphanRemoval = true)
    private ShapeView shapeView;
    @Transient
    public abstract Double computeArea();

    @Transient
    public abstract Double computePerimeter();
}