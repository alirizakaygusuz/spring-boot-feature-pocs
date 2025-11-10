# 🔐 JWT Authentication – Spring Boot POC

This project is a **Proof of Concept (POC)** built with **Spring Boot** to demonstrate **JWT-based stateless authentication** and **multi-language (i18n) error handling**.  
It provides a clean, extendable foundation for secure API authentication using **Spring Security**, **JPA auditing**, and **custom exception handling**.

---

## 🚀 Features

- **JWT Authentication**
  - Access token generation and validation  
  - Stateless configuration (`SessionCreationPolicy.STATELESS`)  
  - Integrated with `JwtAuthenticationFilter` for request validation  
  - Custom `JwtAuthenticationEntryPoint` and `JwtAccessDeniedHandler`  

- **Global Exception Handling**
  - Unified `ErrorResponse` model  
  - Domain-based exception packages (`auth`, `jwt`, `common`)  
  - Built-in localization (i18n) support for error messages  

- **Internationalization (i18n)**
  - English & Turkish translations  
  - Dynamic locale resolution through `Accept-Language` header  
  - Domain-based structure for translation files  

- **Auditing Support**
  - `AuditBase` for automatic `createdAt` / `updatedAt` tracking  
  - Extensible for soft-delete or versioning  

- **Security**
  - Custom `SecurityConfig` with stateless filters  
  - Configurable password encoding (`BCryptPasswordEncoder`)  

- **Clean Architecture**
  - Modular and layered: Controller → Service → Repository  
  - Ready to extend with refresh tokens or role/permission modules  

---

## 📂 Project Structure

```
com.alirizakaygusuz.jwt_poc
 ┣ 📂 auth
 ┃ ┣ 📂 authuser
 ┃ ┃ ┣ 📂 config
 ┃ ┃ ┣ 📂 controller
 ┃ ┃ ┣ 📂 dto
 ┃ ┃ ┣ 📂 exception
 ┃ ┃ ┣ 📂 mapper
 ┃ ┃ ┣ 📂 model
 ┃ ┃ ┣ 📂 repository
 ┃ ┃ ┗ 📂 service
 ┃ ┗ 📂 jwt
 ┃   ┣ 📂 exception
 ┃   ┃ ┗ 📂 type
 ┃   ┃   ┗ JwtCustomException.java
 ┃   ┗ JwtService.java
 ┣ 📂 common
 ┃ ┣ 📂 audit
 ┃ ┃ ┗ AuditBase.java
 ┃ ┣ 📂 config
 ┃ ┣ 📂 exception
 ┃ ┣ 📂 response
 ┃ ┣ 📂 security
 ┃ ┃ ┣ JwtAccessDeniedHandler.java
 ┃ ┃ ┣ JwtAuthenticationEntryPoint.java
 ┃ ┃ ┣ JwtAuthenticationFilter.java
 ┃ ┃ ┣ PasswordConfig.java
 ┃ ┃ ┗ SecurityConfig.java
 ┃ ┗ 📂 util
 ┗ JwtPocApplication.java
```

---

## 🌍 i18n (Internationalization) Structure

```
src/main/resources/i18n/
 ┣ error_auth.properties
 ┣ error_auth_en.properties
 ┗ error_auth_tr.properties
```

### Example Messages

**error_auth_en.properties**
```
error.auth.invalid_credentials=Invalid username or password.
error.auth.account_locked=Your account has been locked.
```

**error_auth_tr.properties**
```
error.auth.invalid_credentials=Kullanıcı adı veya şifre hatalı.
error.auth.account_locked=Hesabınız kilitlendi.
```

Use the `Accept-Language` header to get localized responses:
```
Accept-Language: tr
```

---

## 🧩 Key Components

### 🔑 `JwtService`
Responsible for:
- Generating access tokens  
- Extracting claims  
- Validating expiration and signature  
- Parsing tokens for authentication context  

### 🧱 `SecurityConfig`
Configures:
- Stateless Spring Security  
- JWT filter chain  
- Authentication entry points and access denial handling  

### ⚙️ `JwtAuthenticationFilter`
Intercepts every incoming request:
- Extracts JWT from the Authorization header  
- Validates token signature and expiration  
- Sets authentication context for the request  

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

# i18n
spring.messages.basename=i18n/error_auth
spring.messages.encoding=UTF-8
```

---

## 🧪 Example Flow

1. **Login** → generate JWT token  
2. Include token in each request:  
   ```
   Authorization: Bearer <jwt-token>
   ```
3. If token expires → authentication fails with 401 error  
4. Error messages are localized automatically based on request locale  

---

## 🧰 Tech Stack

- Java  
- Spring Boot  
- Spring Security  
- Spring Data JPA  
- MySQL  
- Lombok  
- i18n (MessageSource)  
- Maven  

---

## 📡 API Testing

You can test all authentication endpoints with Postman using the prepared collection:  
👉 [**JWT-POC Postman Collection**](https://www.postman.com/lunar-module-operator-48760766/springbootprojects/folder/c3hlkd3/jwt-poc?action=share&source=copy-link&creator=39285790&ctx=documentation)

---

## ✍️ Author

**Ali Rıza Kaygusuz**  
💻 Java Backend Developer  
🌐 [GitHub](https://github.com/alirizakaygusuz)  
💼 [LinkedIn](https://www.linkedin.com/in/alirizakaygusuz)

---

## 📄 License

This project is licensed under the **MIT License**.  
You can freely use and modify it for educational or production purposes.
