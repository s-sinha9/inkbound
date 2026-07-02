Inkbound - Your one-stop destination for notebooks, pens, journals, planners, and stationery supplies.

# InkBound - Complete Project Roadmap



# Phase 1 — Monolithic Foundation (8 Hours)

## Module 1: Product Catalog

**Business Features**

* Product CRUD
* Search
* Categories
* Pagination
* Sorting

**Spring Concepts**

* Spring Boot
* REST APIs
* Dependency Injection
* Bean Validation
* Logging
* Exception Handling

**Java**

* OOP
* Collections
* Streams
* Optional

**Design Patterns**

* Layered Architecture
* Repository Pattern
* DTO Pattern

**System Design**

* REST API Design
* Stateless Services
* Pagination
* Offset vs Cursor

---

## Module 2: Customer Management

Features

* Register customer
* View profile
* Update profile

Topics

* Validation
* Relationships
* Entity lifecycle

Patterns

* Builder
* Factory

---

## Module 3: Shopping Cart

Features

* Add item
* Remove item
* Update quantity

Topics

* OneToMany
* ManyToOne
* Cascade
* FetchType

Patterns

* Aggregate Root

System Design

* Domain Modeling

---

## Module 4: Order Management

Features

* Place Order
* Order Status
* Order History

Topics

* Transactions
* Entity Relationships

Patterns

* State Pattern

---

# Phase 2 — Production Spring Boot (8 Hours)

## Module 5: Security

Features

* Login
* Registration
* Protected APIs

Topics

* Spring Security
* Password Encoding
* Filters

---

## Module 6: JWT

Topics

* JWT generation
* Validation
* Refresh Tokens

System Design

* Stateless Authentication

---

## Module 7: OAuth2

Topics

* OAuth2 Fundamentals
* Authorization Code Flow
* Resource Server
* OAuth2 Login
* JWT vs OAuth2
* Role-based Authorization

---

## Module 8: Database Optimization

Topics

* JPA Performance
* Lazy Loading
* N+1 Problem
* Fetch Join
* Entity Graph

System Design

* Indexing
* Query Optimization

---

## Module 9: Redis Caching

Topics

* Spring Cache
* Redis
* TTL
* Eviction

System Design

* Cache Aside
* Cache Invalidation
* Read-through vs Write-through

---

# Phase 3 — Testing & Code Quality (5 Hours)

## Module 10: Unit Testing

Topics

* JUnit 5
* Mockito
* Mocking
* Assertions

---

## Module 11: Integration Testing

Topics

* SpringBootTest
* MockMvc
* H2
* Test Configuration

---

## Module 12: API Testing

Topics

* Postman
* REST Assured

---

## Module 13: Code Coverage

Topics

* JaCoCo
* Testing Strategy

---

# Phase 4 — Concurrency & Multithreading (6 Hours)

## Module 14: Inventory Reservation

Topics

* synchronized
* volatile
* AtomicInteger
* Race Conditions

Business Problem

Two customers buying the last notebook.

---

## Module 15: Flash Sale

Topics

* Thread Pools
* ConcurrentHashMap
* Locks

System Design

* High Contention

---

## Module 16: Background Processing

Topics

* ExecutorService
* CompletableFuture
* Async

---

## Module 17: Java Memory Model

Topics

* Heap
* Stack
* Happens-before
* Visibility
* Thread Safety

---

# Phase 5 — Microservices (6 Hours)

## Module 18: Service Decomposition

Split InkBound into

* Product
* Customer
* Inventory
* Order
* Payment
* Notification

Patterns

* DDD
* Bounded Context

---

## Module 19: Inter-Service Communication

Topics

* REST
* OpenFeign

Patterns

* Circuit Breaker

System Design

* Service Discovery
* Fault Tolerance

---

## Module 20: API Gateway

Topics

* Routing
* Authentication
* Rate Limiting

---

# Phase 6 — Event Driven Systems (5 Hours)

## Module 21: Kafka

Topics

* Producers
* Consumers
* Partitions
* Consumer Groups

---

## Module 22: Order Events

Events

* Order Created
* Inventory Reserved
* Payment Completed
* Notification Sent

---

## Module 23: Distributed Transactions

Patterns

* Saga Pattern
* Outbox Pattern

System Design

* Eventual Consistency

---

## Module 24: Reliability

Topics

* Retry
* Dead Letter Queue
* Message Ordering
* Idempotency

---

# Phase 7 — GraphQL (4 Hours)

We'll expose the **same business logic** through GraphQL while keeping the REST APIs.

## Module 25: GraphQL Basics

Topics

* Schema
* Queries
* Mutations
* Resolvers

---

## Module 26: Advanced GraphQL

Topics

* DataLoader
* N+1 Problem
* Query Complexity
* Authorization
* Pagination

System Design

* REST vs GraphQL
* Choosing the right API style

---

# Phase 8 — Production Readiness (5 Hours)

## Module 27: Docker

Topics

* Dockerfile
* Docker Compose
* Multi-stage builds

---

## Module 28: Kubernetes

Topics

* Pods
* Deployments
* Services
* ConfigMaps
* Secrets
* Health Checks
* Scaling

---

## Module 29: Observability

Topics

* Spring Boot Actuator
* Micrometer
* Prometheus
* Grafana

---

## Module 30: Logging

Topics

* Structured Logging
* Correlation IDs
* Distributed Tracing

---

# Phase 9 — Developer Experience (3 Hours)

## Module 31: API Documentation

Topics

* OpenAPI
* Swagger
* Versioning

---

## Module 32: CI/CD

Topics

* GitHub Actions
* Build Pipelines
* Automated Testing

---

## Module 33: Final Refactoring

Topics

* SOLID Principles
* Design Patterns Review
* Performance Improvements
* Security Review

---

# Topics Covered Throughout the Project

These topics won't have dedicated modules—they'll be discussed whenever they're relevant to the feature we're implementing.

### Core Java

* OOP
* Collections
* Streams
* Generics
* Exception Handling
* Functional Programming
* Concurrency
* Java Memory Model

### Spring Ecosystem

* Spring Core
* Spring Boot
* Spring Data JPA
* Spring Security
* Spring Cache
* Spring Cloud (during microservices)

### System Design

* HLD
* LLD
* CAP Theorem
* Consistency Models
* API Design
* Database Scaling
* Read Replicas
* Sharding
* Load Balancing
* CQRS (Introduction)
* Event Sourcing (Introduction)
* Service Discovery
* API Gateway
* Circuit Breaker
* Bulkhead
* Saga
* Distributed Locks
* Retry
* Dead Letter Queues
* Message Ordering
* High Availability
* Disaster Recovery

### Design Patterns

* Repository
* DTO
* Builder
* Factory
* Singleton (Spring Beans)
* Strategy
* State
* Observer
* Facade
* Decorator
* Adapter
* Circuit Breaker
* Saga

### Performance

* Low Latency APIs
* Connection Pooling
* Caching
* JVM Basics
* Query Optimization
* Indexing
* Lazy Loading
* N+1 Queries

---

## Final Outcome

* A REST API and a GraphQL API backed by the same service layer.
* Authentication with Spring Security, JWT, and OAuth2.
* JPA, Hibernate, Redis, Kafka, and PostgreSQL.
* Concurrency handling for inventory and flash-sale scenarios.
* A microservices architecture with event-driven communication.
* Containerization using Docker and deployment concepts with Kubernetes.
* Observability using Actuator, Micrometer, Prometheus, and Grafana.
* Automated testing, API documentation, and CI/CD.
