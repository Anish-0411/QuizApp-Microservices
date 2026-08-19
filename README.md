# QuizApp-Microservices

A mini microservices-based Quiz Application built using **Java, Spring Boot, and Spring Cloud**. The application is divided into independent services that communicate with each other using REST APIs and OpenFeign.

## Architecture

```text
                        Client
                          |
                          v
                   +--------------+
                   | API Gateway  |
                   +------+-------+
                          |
              +-----------+-----------+
              |                       |
              v                       v
       +-------------+         +----------------+
       | Quiz Service|         |Question Service|
       +------+------+         +--------+-------+
              |                         ^
              |      OpenFeign          |
              +-------------------------+
                          |
                          v
                 +------------------+
                 | Service Registry  |
                 |     Eureka       |
                 +------------------+

## Services

### Quiz Service

- Handles quiz creation and management.
- Retrieves questions from the Question Service.
- Uses OpenFeign for inter-service communication.
- Contains Controller, Service, Repository, and Model layers.

### Question Service

- Manages quiz questions and responses.
- Provides REST APIs for retrieving questions.
- Uses a repository layer for database operations.

### Service Registry

- Uses Eureka Server for service discovery.
- Registers and tracks the available microservices.
- Allows services to discover each other dynamically.

### API Gateway

- Acts as the single entry point for client requests.
- Routes requests to the appropriate microservice.
- Provides centralized request routing.

## Technologies

- Java
- Spring Boot
- Spring Cloud
- Spring Cloud Netflix Eureka
- Spring Cloud Gateway
- OpenFeign
- REST APIs
- JPA / Hibernate
- MySQL
- Maven
- Postman
- Git & GitHub

## Project Structure

```text
QuizApp-Microservices/
│
├── api-gateway/
├── question-service/
├── quiz-service/
├── service-registry/
├── .gitignore
└── README.md
