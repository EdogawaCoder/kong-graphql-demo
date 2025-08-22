# User Service API

> **Status:**  In Progress / 開発中

User Service built with **Spring Boot 3** and **MongoDB**, using DTOs, validation, and global error handling.  
Containerized with Docker, prepared for integration with **Kong API Gateway** and **GraphQL**.

---

## Features
- Create, list, and retrieve users by ID  
- MongoDB with custom ID sequencer  
- Unique email index  
- DTOs with Jakarta Bean Validation  
- Global exception handling (ProblemDetail RFC 7807)  
- Versioned endpoints: `/api/v1/users`

---

## Tech Stack
Java 17 · Spring Boot 3 · MongoDB · Lombok · Docker · Kong (planned) · GraphQL (planned)

---

## Endpoints
| Method | Endpoint             | Description        |
|--------|----------------------|--------------------|
| POST   | `/api/v1/users`      | Create new user    |
| GET    | `/api/v1/users`      | List all users     |
| GET    | `/api/v1/users/{id}` | Get user by ID     |

---

## Run locally
```bash
docker-compose up -d      # start Mongo, Mongo Express, Kong
./mvnw spring-boot:run    # run service
```

Service: `http://localhost:8081/api/v1/users`

---

## Roadmap
- [ ] Redis cache  
- [ ] Kong & GraphQL integration  

---

## License
MIT License
