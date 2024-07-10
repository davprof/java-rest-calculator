package com.example.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    @Autowired
    private HistoryRepository historyRepository;

    public double add(double a, double b) {
        double result = a + b;
        saveHistory(a, b, "add", result);
        return result;
    }

    public double subtract(double a, double b) {
        double result = a - b;
        saveHistory(a, b, "subtract", result);
        return result;
    }

    public double multiply(double a, double b) {
        double result = a * b;
        saveHistory(a, b, "multiply", result);
        return result;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        double result = a / b;
        saveHistory(a, b, "divide", result);
        return result;
    }

    private void saveHistory(double num1, double num2, String operation, double result) {
        History history = new History(num1, num2, operation, result);
        historyRepository.save(history);
    }
}