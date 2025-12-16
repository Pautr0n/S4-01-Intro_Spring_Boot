# 👩‍💻 UserApi — Spring Boot REST Exercise

This repository contains a step‑by‑step exercise to build a minimal but functional REST API using **Spring Boot**.  
The project is divided into three levels of increasing complexity:

- ⭐ **Level 1**: First REST endpoint (`/health`)
- ⭐⭐ **Level 2**: Manage users in memory
- ⭐⭐⭐ **Level 3**: Refactor to layered architecture (**available in branch `level3`**)

---

## 🚀 Technologies Used

- **Java 21**
- **Spring Boot 3.x**
    - Spring Web
    - Spring Boot DevTools
- **Maven** (build & dependency management)
- **JUnit 5** (testing framework)
- **MockMvc** (web layer testing)
- **Mockito** (unit testing with mocks)
- **Postman** (manual HTTP requests)
- **Apache Tomcat** (embedded server)

---
## 📋 Requirements

Before running the project, ensure you have:

- ☕ **Java 21** installed → [Download JDK](https://adoptium.net/)
- 🛠️ **Maven 3.9+** installed → [Download Maven](https://maven.apache.org/download.cgi)
- 🌱 **Spring Boot 3.x** dependencies (handled via Maven)
- 📬 **Postman** (optional, for manual API testing) → [Download Postman](https://www.postman.com/downloads/)

---

## 📂 Project Structure

```
src/main/java/cat/itacademy/s04/t01/userapi
 ├── controllers   		        # REST controllers
 ├── models        		        # Domain models (User, HealthResponse)
 ├── repository  		        # Repository interfaces & implementations
 ├── services      		        # Business logic (Service Layer)
 ├── exceptions		            # Custom exceptions   
 └── UserApiApplication.java  	# Main Spring Boot application
```

---

## ⭐ Level 1 — Health Check Endpoint

- Created a simple `GET /health` endpoint.
- Initially returned plain text `"OK"`.
- Improved to return JSON:
  ```json
  { "status": "OK" }
  ```
- Tested manually via browser and Postman.
- Added automated test with **MockMvc** to verify JSON response.

---

## ⭐⭐ Level 2 — Manage Users in Memory

- Introduced a `User` model with:
    - `id` (UUID)
    - `name` (String)
    - `email` (String)
- Implemented `UserController` with an in‑memory list simulating a database.
- Endpoints:
    - `GET /users` → list all users
    - `POST /users` → create new user (UUID auto‑generated)
    - `GET /users/{id}` → retrieve user by ID (404 if not found)
    - `GET /users?name=...` → filter users by name (case‑insensitive)
- Added **controller tests** with MockMvc to validate all endpoints.

---

## ⭐⭐⭐ Level 3 — Layered Architecture (branch `level3`)

> ⚠️ **Note**: Level 3 implementation is not in `main`.  
> It is available in the branch **`level3`**.

- Refactored into a clean layered architecture:
    - **Controller** → handles HTTP requests
    - **Service** → business logic & rules
    - **Repository** → data access (in‑memory implementation)
- Introduced **UserRepository** interface and `InMemoryUserRepository`.
- Added **UserService** with rules:
    - Prevent duplicate emails (`EmailAlreadyExistsException`).
    - Generate UUIDs for new users.
- Updated `UserController` to delegate to the service layer.
- Tests:
    - **Integration tests** with `@SpringBootTest` + `@AutoConfigureMockMvc`.
    - **Unit tests** for `UserServiceImpl` using **Mockito**.

---

## 🧪 Testing

- **Manual testing**: via Postman (`GET`, `POST` requests).
- **Automated testing**:
    - Web layer tests with MockMvc.
    - Integration tests across all layers.
    - Unit tests with Mockito for service logic.

---

## 📦 Build & Run

Generate the executable JAR:

```bash
mvn clean package
```

Run the application:

```bash
java -jar target/userapi-0.0.1-SNAPSHOT.jar
```

Access endpoints at:

- Health check → [http://localhost:9000/health](http://localhost:9000/health)
- Users API → [http://localhost:9000/users](http://localhost:9000/users)

---

## 🌱 Branches

- **`main`** → Levels 1 & 2 (basic API + in‑memory user management).
- **`level3`** → Refactored layered architecture with service & repository pattern.

---

## 📸 Evidence

- Screenshots of Postman requests and JAR execution are included in the repository as proof of functionality.

---

## ✅ Summary

This exercise demonstrates:
- How to build a REST API with Spring Boot.
- How to evolve from a simple endpoint to a layered architecture.
- Best practices: separation of concerns, testing strategies, and clean commits.

---

## 🤝 Contribution
This project is part of a learning journey. Contributions are welcome!
- Fork the repository
- Create a new branch
- Make your changes and commit them
- Push your branch
- Open a pull request

---
