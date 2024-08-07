# Optional: Install asdf tool-version manager

# Step 1: Set Up Your Development Environment
## Install Java Development Kit (JDK):

Download and install the latest version of JDK from the Oracle website or use OpenJDK.

Ensure Java is properly installed by running java -version in your terminal.

* We covered it with asdf `deps.sh` script

## Install an Integrated Development Environment (IDE):

Download and install an IDE like IntelliJ IDEA, Eclipse, or Visual Studio Code.
* [Eclipse](https://www.eclipse.org/downloads/).

## Install Maven:

Maven is a build automation tool used for Java projects.
Download and install Maven from the official website.

* We covered it with asdf `deps.sh` script

# Step 2: Create a New Spring Boot Project

## Using Spring Initializr:
Go to [Spring Initializr](https://start.spring.io/).

**Select the following options:**

    Project: Maven
    Language: Java
    Spring Boot: (select the latest stable version)
    Project Metadata:
    Group: com.example
    Artifact: calculator
    Name: calculator
    Description: Calculator REST API
    Package Name: com.example.calculator
    Packaging: Jar
    Java: (select the latest version)

**Dependencies: Add the following dependencies:**
    Spring Web

**Click "Generate" to download the project as a zip file.**

**Extract the zip file and open the project in your IDE.**

# Step 3: Create the Calculator Service
Define the CalculatorService:
Create a new Java class CalculatorService inside the com.example.calculator package.

```java
package com.example.calculator;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {
    public double add(double a, double b) {
        return a + b;
    }

    public double subtract(double a, double b) {
        return a - b;
    }

    public double multiply(double a, double b) {
        return a * b;
    }

    public double divide(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Division by zero is not allowed.");
        }
        return a / b;
    }
}
```

# Step 4: Create the Calculator Controller

Define the CalculatorController:
Create a new Java class CalculatorController inside the com.example.calculator package.

```java
package com.example.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/add")
    public double add(@RequestParam double a, @RequestParam double b) {
        return calculatorService.add(a, b);
    }

    @GetMapping("/subtract")
    public double subtract(@RequestParam double a, @RequestParam double b) {
        return calculatorService.subtract(a, b);
    }

    @GetMapping("/multiply")
    public double multiply(@RequestParam double a, @RequestParam double b) {
        return calculatorService.multiply(a, b);
    }

    @GetMapping("/divide")
    public double divide(@RequestParam double a, @RequestParam double b) {
        return calculatorService.divide(a, b);
    }
}
```

# Step 5: Run the Application

Run the Spring Boot Application:
In your IDE, locate the main class CalculatorApplication (generated by Spring Initializr) in the com.example.calculator package.
Right-click the CalculatorApplication class and select "Run" to start the Spring Boot application.

# Step 6: Test the API

Using Postman or Curl:
Open Postman or use Curl to test the API endpoints.

Example endpoints:
GET http://localhost:8080/api/calculator/?operation=add&num1=5&num2=3
GET http://localhost:8080/api/calculator/?operation=subtract&num1=5&num2=3
GET http://localhost:8080/api/calculator/?operation=multiply&num1=5&num2=3
GET http://localhost:8080/api/calculator/?operation=divide&num1=5&num2=3

That's it! You've created a simple calculator backend REST HTTP API using Spring Boot. Let me know if you need any further assistance.