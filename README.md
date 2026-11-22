# InkBound

A Spring Boot backend for a stationery e-commerce platform. Built as a guided engineering project covering REST APIs, persistence, Spring Security, JWT authentication, and distributed systems concepts.

## Tech Stack

- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- JWT
- Gradle
- H2 Database

## Build and Run

### Prerequisites
- Java (version configured in the Gradle build)
- Gradle

### Run the application

```bash
# Clone the repository
git clone <repository-url>
cd inkbound

# Build the project
gradlew clean build

# Run the application
gradlew bootRun
```

The application uses the configuration in `application.properties`. Configure the JWT secret through the `JWT_SECRET` environment variable.

```bash
# Run tests
gradle test
```

## Implemented APIs

| Module | Method | Endpoint | Description |
|---|---|---|---|
| Authentication | POST | `/auth/login` | Authenticate customer and generate JWT |
| Customers | POST | `/customers` | Register a customer |
| Customers | GET | `/customers/{id}` | Retrieve customer details |
| Products | GET | `/products/**` | Product discovery and retrieval |
| Products | POST/PUT/DELETE | `/products/**` | Product management (Admin) |
| Cart | Various | `/customers/{customerId}/cart/**` | View and manage cart items |
| Orders | POST | `/me/orders/**` | Customer order operations |

## Current Features

- Customer registration with BCrypt password hashing.
- Database-backed customer authentication.
- JWT-based authentication.
- Role-based API authorization.
- Product discovery and management.
- Cart management.
- Pending order creation and inventory reservation.
- Transactional outbox foundation.

## Project Status

The backend is under active development. Payment processing, complete order lifecycle management, reliable event delivery, and microservices integration are future implementation tasks.

