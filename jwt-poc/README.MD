# 🔐 JWT-POC – Spring Boot JWT Authentication

This project is a **Proof of Concept (POC)** built with **Spring Boot**  
to demonstrate and practice **JWT-based Authentication and Global Exception Handling**.  
It provides a clean and extensible architecture for handling  
user registration, login, token validation, and error responses.  

---

## 🚀 Features

- **User Registration & Login** → Creates users and returns JWT tokens.  
- **JWT Access Token with Expiration (`exp` claim)** → Secure stateless authentication.  
- **401 Unauthorized Handling** → Missing, invalid, or expired token returns 401.  
- **Custom Exceptions & Error Codes** → Domain-specific authentication and token errors.  
- **Standard API Response** → Unified success & error response format.  
- **Validation Handling** → Field-level error mapping for DTOs.  
- **i18n Support (EN + TR)** → Localized error messages.  
- **Global Exception Handling** → Centralized error processing.  

---

## 📦 Implemented Modules

### 1️⃣ Authentication & JWT Handling

- `AuthController` → Register, Login endpoints.  
- `JwtService` → Generates and validates JWT tokens.  
- `JwtAuthenticationFilter` → Extracts and validates JWT from request headers.  
- `SecurityConfig` → Configures Spring Security with JWT filter chain.  

### 2️⃣ Error Codes Strategy

- Error codes grouped under `AuthErrorCodes` and `JwtErrorCodes`.  
- Example:  
  - **1001 → AUTH_USER_NOT_FOUND**  
  - **1002 → AUTH_USERNAME_ALREADY_EXISTS**  
  - **1003 → AUTH_EMAIL_ALREADY_EXISTS**  
  - **1006 → AUTH_INVALID_PASSWORD**  
  - **2001 → JWT_TOKEN_EXPIRED**  
  - **2002 → JWT_TOKEN_INVALID**  

### 3️⃣ Validation Handling

- DTOs annotated with Jakarta Bean Validation (`@NotNull`, `@Email`, etc.).  
- Invalid requests return detailed `fieldErrors`.  

### 4️⃣ Response Models

- **Success responses** → `StandardApiResponse<T>`  
- **Error responses** → `ErrorResponse<T>`  

---

## 📂 Project Structure

```
com.alirizakaygusuz.jwt_poc
 ┣ 📂 auth
 ┃ ┣ 📂 authuser
 ┃ ┃ ┣ 📂 config         # AuthenticationConfig
 ┃ ┃ ┣ 📂 controller     # AuthController
 ┃ ┃ ┣ 📂 dto            # LoginRequest, LoginResponse, RegisterRequest, RegisterResponse
 ┃ ┃ ┣ 📂 exception      # Auth-specific exceptions & error codes
 ┃ ┃ ┣ 📂 mapper         # AuthMapper
 ┃ ┃ ┣ 📂 model          # AuthUser entity
 ┃ ┃ ┣ 📂 repository     # AuthUserRepository
 ┃ ┃ ┣ 📂 service        # AuthService, AuthServiceImpl
 ┃ ┣ 📂 jwt
 ┃ ┃ ┣ 📂 exception      # Jwt-specific exceptions & error codes
 ┃ ┃ ┗ JwtService.java   # JWT creation & validation
 ┣ 📂 common
 ┃ ┣ 📂 audit            # Audit base classes
 ┃ ┣ 📂 config           # JacksonConfig, I18nConfig
 ┃ ┣ 📂 exception        # BaseException, GlobalExceptionHandler
 ┃ ┣ 📂 response         # ErrorResponse, StandardApiResponse
 ┃ ┣ 📂 security         # SecurityConfig, JwtAuthenticationFilter, EntryPoint, AccessDeniedHandler
 ┃ ┣ 📂 util             # HostUtils, MessageUtils
 ┗ JwtPocApplication.java
```

---

## 📡 API Endpoints

- `POST /api/v1/auth/register` → Register new user  
- `POST /api/v1/auth/login` → Login & receive JWT  
- `GET /api/v1/auth` → **Secured endpoint** (requires valid JWT in Authorization header)  

---

## 🧪 Example Requests & Responses

### ✅ Register – Success

```json
{
  "status": "CREATED",
  "payload": {
    "username": "aliriza",
    "email": "aliriza@example.com"
  },
  "timestamp": "2025-10-04T00:32:25.387025",
  "code": 201
}
```

### ❌ Register – Username Already Exists

```json
{
  "code": 1002,
  "i18nKey": "error.auth.username_already_exists",
  "path": "/api/v1/auth/register",
  "timestamp": "2025-10-04T00:32:41.821624",
  "hostName": "Ali-MacBook-Pro.local",
  "message": "Username already exists"
}
```

### ❌ Register – Invalid Email

```json
{
  "path": "/api/v1/auth/register",
  "timestamp": "2025-10-03T23:50:38.030723",
  "hostName": "Ali-MacBook-Pro.local",
  "message": "Validation failed",
  "fieldErrors": {
    "email": "Please provide a valid email address"
  }
}
```

### ✅ Login – Success

```json
{
  "status": "OK",
  "payload": {
    "username": "aliriza",
    "email": "aliriza@example.com",
    "accessToken": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9....",
    "tokenType": "Bearer",
    "expiresIn": 3600
  },
  "timestamp": "2025-10-04T00:17:35.831291",
  "code": 200
}
```

### ❌ Login – Invalid Password

```json
{
  "code": 1006,
  "i18nKey": "error.auth.password_invalid",
  "path": "/api/v1/auth/login",
  "timestamp": "2025-10-03T23:51:06.480579",
  "hostName": "Ali-MacBook-Pro.local",
  "message": "Invalid password"
}
```

### ❌ Login – User Not Found

```json
{
  "code": 1001,
  "i18nKey": "error.auth.not_found",
  "path": "/api/v1/auth/login",
  "timestamp": "2025-10-04T00:32:58.716568",
  "hostName": "Ali-MacBook-Pro.local",
  "message": "User not found"
}
```

### ❌ Access Secured Endpoint Without Token

```json
{
  "error": "Unauthorized - Token is missing or invalid"
}
```

---

## 🛠️ Tech Stack

- **Java 17**  
- **Spring Boot 3.x**  
- **Spring Security (JWT Authentication)**  
- **JJWT (io.jsonwebtoken)** – JWT generation & validation  
- **Spring Data JPA** – Persistence  
- **H2 Database** – In-memory DB for testing  
- **Lombok** – Boilerplate reduction  
- **Jakarta Validation** – Request validation  
- **Maven** – Build tool  

---
## 📡 API Testing

A Postman collection is prepared for this project:  
[**Exception-POC Postman Collection**](https://www.postman.com/lunar-module-operator-48760766/springbootprojects/folder/c3hlkd3/spring-boot-feature-pocs?action=share&creator=39285790)

---

## ▶️ How to Run

```bash
# 1. Clone the project
git clone https://github.com/alirizakaygusuz/spring-boot-feature-pocs.git
cd spring-boot-feature-pocs/jwt-poc

# 2. Build and run
./mvnw clean install
./mvnw spring-boot:run
```

App will start at: [http://localhost:8080](http://localhost:8080)

---

## ✍️ Author

**Ali Rıza Kaygusuz** – 🛠️ Java Backend Developer  
🌐 [GitHub Profile](https://github.com/alirizakaygusuz)  
💼 [LinkedIn Profile](https://www.linkedin.com/in/alirizakaygusuz)

---

## 📄 License

This project is licensed under the **MIT License**.  
📃 [MIT License Link](https://opensource.org/licenses/MIT)
