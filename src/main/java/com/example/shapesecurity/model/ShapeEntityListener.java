package com.example.shapesecurity.model;

import com.example.shapesecurity.model.shape.Shape;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class ShapeEntityListener {

    @PrePersist
    public void prePersist(Shape shape) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            shape.setCreatedBy(authentication.getName());
            shape.setCreatedAt(LocalDateTime.now());
        }
    }

    @PreUpdate
    public void preUpdate(Shape shape) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            shape.setLastModifiedBy(authentication.getName());
            shape.setLastModifiedAt(LocalDateTime.now());
        }
    }
}
