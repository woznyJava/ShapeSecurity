package com.example.shapesecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "userAuditorAware")
public class ShapeSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShapeSecurityApplication.class, args);
    }

}
