package com.example.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/calculate")
    public double operation(@RequestParam String operation, @RequestParam double num1, @RequestParam double num2) {
        switch (operation) {
            case "add": return calculatorService.add(num1, num2);
            case "subtract": return calculatorService.subtract(num1, num2);
            case "multiply": return calculatorService.multiply(num1, num2);
            case "divide": return calculatorService.divide(num1, num2);
        }
        throw new RuntimeException("Invalid");
    }
}