# Ecommerce Project

A Spring Boot application for ecommerce management, featuring:

User registration and role-based access (ADMIN/USER)

Product management with CRUD operations

Order processing with dynamic total calculation and stock management

Reporting: top users, average ticket per user, total revenue per month

JWT-based authentication (planned for future implementation)

Future updates: The system will evolve into a microservices architecture with event-driven asynchronous communication, message queues for reliable event handling, distributed transaction management, real-time analytics, and cloud-friendly deployment with containerization and horizontal scaling.

## Prerequisites

- Java 21+
- Docker & Docker Compose (for MySQL and RabbitMQ)
- Maven

## Setup & Running

1. **Start MySQL and RabbitMQ:**


docker-compose up -d
```

2. **Build the project:**

   ```powershell
./mvnw clean install
```

3. **Run the application:**

./mvnw spring-boot:run
```

   The API will be available at: `http://localhost:8080/ecommerce`

## Authentication

- Register a user via `/ecommerce/user/register`.
- Login via `/ecommerce/auth/login` to receive JWT tokens.
- Use the `Authorization: Bearer <token>` header for protected endpoints.

---

## API Endpoints

### UserController

- **POST `/ecommerce/user/register`**
  - Registers a new user.
  - **Body:**
    ```json
    {
      "name": "User Name",
      "email": "user@email.com",
      "password": "password"
    }
    ```
  - **Response:** User details.

### LoginController

- **POST `/ecommerce/auth/login`**
  - Authenticates a user and returns JWT tokens.
  - **Body:**
    ```json
    {
      "email": "user@email.com",
      "password": "password"
    }
    ```
  - **Response:** Access and refresh tokens.

### ProductController

- **POST `/ecommerce/product/create`**
  - Creates a new product.
  - **Body:**
    ```json
    {
      "name": "Product Name",
      "description": "Description",
      "price": 100.0,
      "category": "CATEGORY_1",
      "stock": 10
    }
    ```
  - **Response:** Product details.

- **GET `/ecommerce/product`**
  - Lists all products.

- **GET `/ecommerce/product/{id}`**
  - Gets product by UUID.

- **PUT `/ecommerce/product/{id}/update`**
  - Updates product details.
  - **Body:** Same as create.

- **DELETE `/ecommerce/product/{id}`**
  - Deletes a product.

### OrderController

- **POST `/ecommerce/order/new`**
  - Creates a new order.
  - **Body:**
    ```json
    {
      "items": [
        { "productId": "uuid", "quantity": 2 }
      ]
    }
    ```
  - **Response:** Order details.

- **POST `/ecommerce/order/{orderId}/pay`**
  - Pays for an order.

- **GET `/ecommerce/order/user/{userId}`**
  - Lists orders for a user.

### ReportController

- **GET `/ecommerce/report/top-users`**
  - Gets top 5 users by purchase.

- **GET `/ecommerce/report/average-ticket`**
  - Gets average ticket per user.

- **GET `/ecommerce/report/revenue-month`**
  - Gets total revenue for the current month.

---

## Notes

- All endpoints (except `/ecommerce/auth/login` and `/ecommerce/user/register`) require JWT authentication.
- RabbitMQ is used for order event messaging.
- Database migrations are managed by Flyway.

---
