# Salary Tracker API


A REST API for tracking salaries, recurring expenses, and monthly summaries.  
I built this project to solve a personal problem — I needed a way to keep track of my salary and fixed expenses. At the same time, I wanted to practice **Quarkus** on a real use case.

![Quarkus](https://img.shields.io/badge/Quarkus-3.21.1-blue)
![Java](https://img.shields.io/badge/Java-21-orange)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)


##  Overview
The Salary Tracker API helps manage:
- Base salary and adjustments
- Recurring fixed expenses (e.g. rent, loans)
- One-time expenses
- Monthly summaries of income vs expenses

It’s a simple but practical backend that reflects both a real need and my learning journey with Quarkus.
### Key Features

Features
- User authentication with JWT
- Salary management
- Expense categories
- Recurring expenses
- Monthly financial summaries
- Dockerized for easy deployment

## Architecture

The application follows a layered architecture:

- **Resource Layer**: REST endpoints for handling HTTP requests
- **Service Layer**: Business logic implementation
- **Repository Layer**: Data access using Panache
- **Model Layer**: Domain entities
- **DTO Layer**: Data transfer objects for API communication
- **Security Layer**: JWT authentication and authorization

### Tech Stack
- Java 21
- Quarkus 3 (RESTEasy, Hibernate Panache)
- MySQL 8
- JWT for authentication
- Docker

### Database Setup

1. Create a MySQL database:

```sql
CREATE DATABASE salary_tracker_app;
CREATE USER 'salary_user'@'localhost' IDENTIFIED BY 'your_password';
GRANT ALL PRIVILEGES ON salary_tracker_app.* TO 'salary_user'@'localhost';
FLUSH PRIVILEGES;
```

2. Update the database configuration in `src/main/resources/application.properties` if needed.

### Running the Application

#### Development Mode

```shell
./mvnw quarkus:dev
```

This enables live coding with:
- Hot reload for code changes
- Dev UI at http://localhost:8080/q/dev/
- Swagger UI at http://localhost:8080/q/swagger-ui/

#### Production Mode

```shell
./mvnw package
java -jar target/quarkus-app/quarkus-run.jar
```
##  Security

The API uses JWT (JSON Web Token) for authentication. To access protected endpoints:

1. Register a user using the `/api/v1/users/register` endpoint
2. Login with the created user at `/api/v1/users/login` to receive a JWT token
3. Include the token in the `Authorization` header for subsequent requests:
```
Authorization: Bearer your_jwt_token
 ```

##  API Endpoints

### User Management
- `POST /api/v1/users/register` - Register a new user
- `POST /api/v1/users/login` - Login and get JWT token
- `GET /api/v1/users/me` - Get current user information

### Salary Management
- `GET /api/v1/salaries` - Get all salaries
- `GET /api/v1/salaries/{id}` - Get salary by ID
- `POST /api/v1/salaries` - Create a new salary
- `PUT /api/v1/salaries/{id}` - Update a salary
- `DELETE /api/v1/salaries/{id}` - Delete a salary

### Category Management
- `GET /api/v1/categories` - Get all categories
- `GET /api/v1/categories/{id}` - Get category by ID
- `POST /api/v1/categories` - Create a new category
- `PUT /api/v1/categories/{id}` - Update a category
- `DELETE /api/v1/categories/{id}` - Delete a category

### Expense Management
- `GET /api/v1/expenses` - Get all expenses
- `GET /api/v1/expenses/{id}` - Get expense by ID
- `POST /api/v1/expenses` - Create a new expense
- `PUT /api/v1/expenses/{id}` - Update an expense
- `DELETE /api/v1/expenses/{id}` - Delete an expense

### Recurring Expense Management
- `GET /api/v1/recurringExpenses` - Get all recurring expenses
- `GET /api/v1/recurringExpenses/{id}` - Get recurring expense by ID
- `POST /api/v1/recurringExpenses` - Create a new recurring expense
- `PUT /api/v1/recurringExpenses/{id}` - Update a recurring expense
- `DELETE /api/v1/recurringExpenses/{id}` - Delete a recurring expense

### Summary
- `GET /api/v1/summary/{year}/{month}` - Get financial summary for a specific month

API documentation is available via Swagger UI when the application is running:

- Development: http://localhost:8080/q/swagger-ui/
- Production: https://your-domain.com/q/swagger-ui/

