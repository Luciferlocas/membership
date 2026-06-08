# Membership Service

A Spring Boot REST API for managing membership subscriptions, tiers, and configurable benefits.

## Features

* Monthly, Quarterly, and Yearly membership plans
* Silver, Gold, and Platinum tiers
* Configurable tier-based benefits
* Create subscription
* View current subscription
* Upgrade/Downgrade tier
* Cancel subscription
* Swagger API documentation

## Tech Stack

* Java 21
* Spring Boot
* Spring Data JPA
* PostgreSQL (Neon)
* Hibernate
* Lombok
* Swagger/OpenAPI

## Architecture

```text
Controller → Service → Repository → PostgreSQL
```

## Key Design Decisions

* DTOs used instead of exposing JPA entities
* Global exception handling
* Transaction management using `@Transactional`
* Optimistic locking using `@Version`
* Single active subscription per user
* Configurable benefits stored in the database

## API Documentation

Swagger UI:

```text
http://localhost:8080/docs
```

## Main Endpoints

```http
GET    /api/memberships/plans
POST   /api/users
POST   /api/subscriptions
GET    /api/subscriptions/current/{userId}
PUT    /api/subscriptions/tier
PUT    /api/subscriptions/cancel/{subscriptionId}
```

## Sample Response

```json
{
  "subscriptionId": "...",
  "userId": "...",
  "planType": "MONTHLY",
  "tierType": "PLATINUM",
  "benefits": [
    "DISCOUNT_15",
    "FREE_DELIVERY",
    "PRIORITY_SUPPORT"
  ],
  "status": "ACTIVE",
  "startDate": "2026-06-08",
  "expiryDate": "2026-07-08"
}
```

## Run Locally

```bash
./mvnw spring-boot:run
```
