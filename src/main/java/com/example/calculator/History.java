package com.example.calculator;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "history")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double num1;
    private double num2;
    private String operation;
    private double result;
    private LocalDateTime timestamp;

    public History() {}

    public History(double num1, double num2, String operation, double result) {
        this.num1 = num1;
        this.num2 = num2;
        this.operation = operation;
        this.result = result;
        this.timestamp = LocalDateTime.now();
    }

    // Getters and setters omitted for brevity
}