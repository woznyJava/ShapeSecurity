package com.example.shapesecurity.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Getter
public class ErrorResponse {
    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final String message;
    private final List<String> details;
}
