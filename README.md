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
    Mysql JDBC Driver
    Spring Data JPA

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
```

# Step 5: Create the Database

In your database shell create the table:


```sql
    CREATE DATABASE IF NOT EXISTS calculator_db;

    CREATE TABLE IF NOT EXISTS calculator_db.history (
        id INT AUTO_INCREMENT PRIMARY KEY,
        num1 FLOAT NOT NULL,
        num2 FLOAT NOT NULL,
        operation VARCHAR(10) NOT NULL,
        result FLOAT NOT NULL,
        timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
    );

    TRUNCATE TABLE calculator_db.history;

    SELECT * from calculator_db.history;
```

# Step 6: Configure MySQL Connection in Spring Boot

Add the following properties to your `application.properties` file:

```
spring.datasource.url=jdbc:mysql://localhost:3306/calculator_db
spring.datasource.username=root
spring.datasource.password=mysql
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

# Step 7: Create Entity and Repository

## Create the History Entity:

Create a new Java class `History` inside the com.example.calculator package:

```java
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
```

## Create the History Repository:

Create a new interface `HistoryRepository` inside the com.example.calculator package:

```java
package com.example.calculator;

import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoryRepository extends JpaRepository<History, Long> {
}
```

## Update your CalculatorService

```java
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
```

# Step 8: Run the Application

Run the Spring Boot Application:
In your IDE, locate the main class CalculatorApplication (generated by Spring Initializr) in the com.example.calculator package.
Right-click the CalculatorApplication class and select "Run" to start the Spring Boot application.

# Step 9: Test the API

Using Postman or Curl:
Open Postman or use Curl to test the API endpoints.

Example endpoints:

GET http://localhost:8080/api/calculate?operation=add&num1=5&num2=3

GET http://localhost:8080/api/calculate?operation=subtract&num1=5&num2=3

GET http://localhost:8080/api/calculate?operation=multiply&num1=5&num2=3

GET http://localhost:8080/api/calculate?operation=divide&num1=5&num2=3

That's it! You've created a simple calculator backend REST HTTP API using Spring Boot. Let me know if you need any further assistance.