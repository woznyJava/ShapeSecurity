package com.example.shapesecurity.model;

import com.example.shapesecurity.model.shape.Shape;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.PrePersist;
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
}
