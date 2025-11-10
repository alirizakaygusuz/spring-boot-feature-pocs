# 🔐 JWT Permission-Based Authentication & Authorization – Spring Boot POC

This project is a **Proof of Concept (POC)** built with **Spring Boot 3** to demonstrate **JWT-based authentication**, **permission-driven RBAC (Role-Based Access Control)**, **i18n (internationalization)**, and **refresh token rotation**.  
It provides a **stateless and secure authentication system** with features like **global exception handling**, **AOP logging**, **multi-language error handling**, and **scheduled refresh token cleanup**.

---

## 🚀 Features

- **JWT Authentication & Authorization**
  - Access & Refresh token pair generation  
  - **Refresh token rotation** (one-time use, new pair generated on refresh)  
  - Stateless security configuration (no HTTP sessions)

- **Permission-Based RBAC**
  - Fine-grained permission checks using `@PreAuthorize("hasAuthority('<permission>')")`
  - Predefined permissions and roles seeded at startup  
  - User-role and role-permission relations modeled via join tables

- **Internationalization (i18n)**
  - Multi-language error message support (English & Turkish)
  - Messages organized under `src/main/resources/i18n`
  - Automatically resolves locale via `Accept-Language` header

- **Refresh Token Lifecycle**
  - Revocation on logout or rotation  
  - Automatic daily cleanup of expired/revoked tokens via scheduled tasks  

- **Security Layer**
  - Custom `JwtAuthenticationFilter` for token validation  
  - `JwtAccessDeniedHandler` (403) and `JwtAuthenticationEntryPoint` (401)  
  - Fully stateless configuration using `SessionCreationPolicy.STATELESS`

- **Exception Handling & Validation**
  - Centralized global exception handler  
  - Unified API response wrapper (`StandardApiResponse<T>`)  
  - Bean validation on DTOs with detailed field-level error mapping

- **Observability**
  - `LoggingAspect` for tracking service-layer invocations and exceptions  
  - Utilities for capturing IP, hostname, and client metadata  

---

## 📦 Implemented Modules

### 1️⃣ Authentication
Handles user login and JWT token generation.  
Includes:
- `AuthUserController`, `AuthUserService`, `AuthUser` entity  
- DTOs for login, update, and response  
- Custom `AuthException` and `AuthErrorType`  

### 2️⃣ Refresh Token Management
Implements rotation, revocation, and scheduled cleanup of refresh tokens.
- Endpoints:
  - `/api/v1/auth/refresh-token/rotate`
  - `/api/v1/auth/logout`
- Auto-revokes old refresh tokens and issues new pairs securely  
- `RefreshTokenScheduler` cleans up expired/revoked tokens daily  

### 3️⃣ Authorization (Permissions & Roles)
Defines roles, permissions, and their relationships:
- `PredefinedPermission` enum (granular authority definitions)
- `Role`, `Permission`, `RolePermission`, `RoleUser` entities
- Seeder services initialize system roles, permissions, and mock users

### 4️⃣ Common Infrastructure
Cross-cutting concerns like AOP, auditing, config, and error handling:
- **AOP:** `LoggingAspect`  
- **Audit:** `AuditBase`, `SoftDeletableAuditBase`  
- **Security:** JWT filter & config classes  
- **Response Handling:** `BaseResponseController`, `StandardApiResponse`  
- **i18n:** Folder structure, locale resolver, and localized property files  

---

## 🌍 i18n (Internationalization) Folder Structure

```
src/main/resources/i18n/
 ┣ 📂 auth
 ┃ ┣ error_auth.properties
 ┃ ┣ error_auth_en.properties
 ┃ ┗ error_auth_tr.properties
 ┣ 📂 permission
 ┃ ┣ error_permission.properties
 ┃ ┣ error_permission_en.properties
 ┃ ┗ error_permission_tr.properties
 ┣ 📂 role
 ┃ ┣ error_role.properties
 ┃ ┣ error_role_en.properties
 ┃ ┗ error_role_tr.properties
```

Each folder corresponds to a domain (auth, permission, role).  
Example keys from `error_auth_en.properties`:
```
error.auth.invalid_credentials=Invalid username or password.
error.auth.account_locked=Your account has been locked.
```
And in `error_auth_tr.properties`:
```
error.auth.invalid_credentials=Kullanıcı adı veya şifre hatalı.
error.auth.account_locked=Hesabınız kilitlendi.
```

Locale is automatically resolved using the `Accept-Language` HTTP header.  
Example:
```http
Accept-Language: tr
```

---

## 📡 API Endpoints

### 🔑 Authentication
**POST** `/api/v1/auth/login`  
Authenticate a user and issue access & refresh tokens.  
```json
{
  "usernameOrEmail": "admin",
  "password": "password123"
}
```
**Response:**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1...",
  "refreshToken": "b4a7b3a5-...",
  "expiresIn": 900
}
```

---

### 👤 User Management
| Method | Endpoint | Description |
|--------|-----------|-------------|
| GET | `/api/v1/auth/users/me` | Get authenticated user details |
| PATCH | `/api/v1/auth/users/me` | Update user profile |
| DELETE | `/api/v1/auth/users/me` | Delete own account |

---

### 🛡️ Admin Endpoints
| Method | Endpoint | Description |
|--------|-----------|-------------|
| GET | `/api/v1/auth/users` | Get all users |
| GET | `/api/v1/auth/users/{id}` | Get user by ID |
| GET | `/api/v1/auth/users/by-username?value=...` | Get user by username or email |
| PATCH | `/api/v1/auth/users/{id}` | Update user by ID |
| DELETE | `/api/v1/auth/users/{id}` | Delete user by ID |
| DELETE | `/api/v1/auth/admin/me` | Delete admin user |

> Requires appropriate permissions like `admin:user:view`, `admin:user:update`, `admin:user:delete`.

---

### 🔁 Refresh Token
| Method | Endpoint | Description |
|--------|-----------|-------------|
| POST | `/api/v1/auth/refresh-token/rotate` | Rotate refresh token and issue a new pair |
| POST | `/api/v1/auth/logout` | Revoke current refresh token |

---

## 👤 Seeded Accounts (via Seeder)

The project boots with **mock users** created by the seeder in `authorization.bootstrap.seeder.*`:

| Account | Username | Email | Role | Permissions (high level) | Default Password |
|:--------|:----------|:------|:------|:--------------|:--------------|
| Admin | `admin` | `admin@example.com` | `SUPER_ADMIN` | `admin:user:*`, `admin:role:*`, `admin:permission:view_all` | `admin` |
| User | `user` | `user@example.com` | `USER` | `user:self:view`, `user:self:update`, `user:self:delete` | `user` |

### 🔐 Access Scope per Role

#### USER (`user`, role: USER)
✅ Can access:
- `POST /api/v1/auth/login`
- `POST /api/v1/auth/refresh-token/rotate`
- `POST /api/v1/auth/logout`
- `GET /api/v1/auth/users/me`
- `PATCH /api/v1/auth/users/me`
- `DELETE /api/v1/auth/users/me`

🚫 Forbidden:
- All `/admin` endpoints  
- Any `/users` access except self

#### ADMIN (`admin`, role: SUPER_ADMIN)
✅ Can access:
- Everything above +
- `GET /api/v1/auth/users`, `/by-username`, `/by-id`
- `PATCH /api/v1/auth/users/{id}`
- `DELETE /api/v1/auth/users/{id}`, `/by-username`, `/admin/me`

### Quick Login Examples
```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "usernameOrEmail": "admin",
  "password": "admin"
}
```

```http
POST /api/v1/auth/login
Content-Type: application/json

{
  "usernameOrEmail": "user",
  "password": "user"
}
```

---

## 🧹 Scheduled Tasks

**`RefreshTokenScheduler`**
- Runs daily at 03:15 AM
- Revokes expired refresh tokens  
- Deletes revoked tokens older than `jwt.refresh.token.cleanup.days`  

---

## ⚙️ Configuration

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/jwt_poc?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

# JWT
jwt.secret.key=your-256-bit-secret
jwt.access.token.expiration-minutes=15
jwt.refresh.token.expiration-minutes=10080
jwt.refresh.token.cleanup.days=30

# i18n
spring.messages.basename=i18n/messages
spring.messages.encoding=UTF-8
```

---

## 🛠️ Tech Stack

- Java 17  
- Spring Boot 3.5.x  
- Spring Security 6  
- Spring Data JPA (Hibernate)  
- MySQL  
- Lombok  
- Jakarta Validation  
- AOP (LoggingAspect)  
- i18n (Internationalization)  
- Maven  

---

## 📡 API Testing

A Postman collection is prepared for this project:  
[**JWT-PERMISSION-BASED-POC Postman Collection**](https://www.postman.com/lunar-module-operator-48760766/springbootprojects/folder/enbo4hy/jwt-permission-based)


## 🧪 Example Flow

1. **Login** → receive `accessToken` + `refreshToken`  
2. Use `accessToken` for secured endpoints  
3. When access token expires → call `/refresh-token/rotate` with refresh token  
4. Receive a new token pair  
5. Call `/logout` to revoke refresh token  
6. Daily scheduler cleans up expired tokens  

---

## ✍️ Author

**Ali Rıza Kaygusuz**  
💻 Java Backend Developer  
🌐 [GitHub Profile](https://github.com/alirizakaygusuz)  
💼 [LinkedIn Profile](https://www.linkedin.com/in/alirizakaygusuz)

---

## 📄 License

This project is licensed under the **MIT License**.
