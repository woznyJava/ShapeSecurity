package com.example.shapesecurity.config;

import com.example.shapesecurity.mapper.ShapeMapper;
import com.example.shapesecurity.mapper.ShapeMapperImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapStructConfiguration {

    @Bean
    public ShapeMapper shapeMapper() {
        return new ShapeMapperImpl();
    }
}


