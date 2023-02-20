package com.example.shapesecurity.controller;

import com.example.shapesecurity.model.FilterRequest;
import com.example.shapesecurity.model.command.CreateShapeCommand;
import com.example.shapesecurity.model.command.UpdateShapeCommand;
import com.example.shapesecurity.model.dto.ShapeDto;
import com.example.shapesecurity.model.shape.Shape;
import com.example.shapesecurity.service.ShapeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/shapes")
public class ShapeController {
    private final ShapeService shapeService;

    @PreAuthorize("hasRole('ROLE_CREATOR')")
    @PostMapping
    public ResponseEntity<ShapeDto> saveShape(@RequestBody CreateShapeCommand createRequest) {
        ShapeDto save = shapeService.save(createRequest);
        return ResponseEntity.status(HttpStatus.CREATED.value()).body(save);
    }
    @GetMapping
    public ResponseEntity<List<Shape>> filter(FilterRequest filterRequest) {
        return ResponseEntity.status(HttpStatus.OK.value()).body(shapeService.filter(filterRequest));
    }
    @PutMapping
    public ResponseEntity<ShapeDto> update(@RequestBody UpdateShapeCommand updateShapeCommand) throws InterruptedException {
        return ResponseEntity.status(HttpStatus.OK.value()).body(shapeService.update(updateShapeCommand));
    }
}
