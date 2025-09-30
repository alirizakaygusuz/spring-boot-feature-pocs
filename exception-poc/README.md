# 🛡️ Exception-POC – Spring Boot Global Exception Handling

This project is a **Proof of Concept (POC)** built with **Spring Boot**  
to demonstrate and practice **Global Exception Handling, i18n
(internationalization), validation, and AOP logging**.  
It provides a clean and extensible architecture for handling
application-wide errors with localized messages.

---

## 🚀 Features

- **Global Exception Handling** → Centralized handling for all exceptions.  
- **Custom Exceptions & Error Types** → Domain-specific exceptions with i18n keys.  
- **Error Codes Strategy** → Organized error code ranges for each domain.  
- **Standard API Response** → Unified success & error response format.  
- **Validation Handling** → Automatic validation error mapping with field-level messages.  
- **i18n Support** → Localized error messages (EN + TR).  
- **AOP Logging** → Logs method calls and exceptions across service layer.  
- **DTO ↔ Entity Mapping** → Instead of manual mapping, **ModelMapper** is used.

---

## 📦 Implemented Modules

### 1️⃣ Global Exception Handling

- `GlobalExceptionHandler` catches all `BaseException` and validation errors.  
- Returns `ErrorResponse` with code, i18nKey, message, path, and timestamp.

**Example Response (409 Conflict):**
```json
{
  "code": "1002",
  "i18nKey": "error.username.already_exists",
  "path": "/api/v1/users",
  "timestamp": "2025-09-30T23:32:32.610709",
  "hostName": "Ali-MacBook-Pro.local",
  "message": "Username already exists: ali"
}
```

---

### 2️⃣ Error Codes Strategy

- Error codes are grouped by domain using **`ErrorCodeRanges`**.  
- Example:  
  - **1000–1999 → User errors**  
  - **2000–2999 → Account errors**  
- Each domain has its own `ErrorType` enum (e.g., `UserErrorType`).  
- Each error type contains:  
  - **code** (numeric error code)  
  - **i18nKey** (for translation lookup)  
  - **HttpStatus** (for REST response status)

This ensures **consistency, scalability.**.

---

### 3️⃣ i18n (Internationalization)

- Messages stored under `src/main/resources/i18n/`.  
- Supports **English (`error_user_en.properties`)** and **Turkish (`error_user_tr.properties`)**.  
- Uses `Accept-Language` header to resolve locale.

**Example Request:**
```http
POST /api/v1/users
Accept-Language: tr
```

**Response:**
```json
{
  "code": "1002",
  "i18nKey": "error.username.already_exists",
  "message": "Kullanıcı adı zaten mevcut: ali"
}
```

---

### 4️⃣ Response Models Separation

- **Success responses** → `StandardApiResponse<T>`  
  - Fields: `status`, `payload`, `timestamp`.  
- **Error responses** → `ErrorResponse<T>`  
  - Fields: `code`, `i18nKey`, `message`, `path`, `hostName`, `timestamp`, `fieldErrors`.  

This separation makes API responses **clearer and more maintainable**.

---

### 5️⃣ Validation Handling

- DTOs use Jakarta Bean Validation annotations (`@NotNull`, `@Email`, etc.).  
- Validation errors collected and returned as `fieldErrors` map.

**Example Invalid Request:**
```json
{
  "username": "aliBaba",
  "email": "alibabaexample.com",
  "password": "123456789",
  "firstName": "AliBaba",
  "lastName": "Rıza"
}
```

**Response (400 Bad Request):**
```json
{
  "path": "/api/v1/users/1",
  "timestamp": "2025-09-30T23:37:32.486012",
  "hostName": "Ali-MacBook-Pro.local",
  "message": "Validation failed",
  "fieldErrors": {
    "email": "must be a well-formed email address"
  }
}
```

---

### 6️⃣ AOP Logging

- `LoggingAspect` intercepts service methods.  
- Logs exceptions with method signature and error details.

**Example Logs:**
```
[After Throwing] Method: UserServiceImpl.createUser(..) threw exception. 
 Type: UsernameAlreadyExistsException , Message: error.username.already_exists
```

---

### 7️⃣ Utility Layer

- **MessageUtils** → Resolves i18n messages from property files.  
- **HostUtils** → Attaches server hostname to error responses.  
  - Useful for debugging in distributed or containerized environments.

---

## 📂 Project Structure

```
com.alirizakaygusuz.exception_poc
 ┣ 📂 common
 ┃ ┣ 📂 aspect        # LoggingAspect
 ┃ ┣ 📂 audit         # Base audit entity
 ┃ ┣ 📂 config        # i18n configuration
 ┃ ┣ 📂 exception     # BaseException, GlobalExceptionHandler, ErrorType enums
 ┃ ┣ 📂 mapper        # ModelMapper configuration
 ┃ ┣ 📂 responnse     # ErrorResponse, StandardApiResponse
 ┃ ┣ 📂 util          # Utility classes (HostUtils, MessageUtils)
 ┣ 📂 user
 ┃ ┣ 📂 controller    # REST controller (UserController)
 ┃ ┣ 📂 dto           # Request & Response DTOs
 ┃ ┣ 📂 exception     # User-specific exceptions & error codes
 ┃ ┣ 📂 model         # User entity
 ┃ ┣ 📂 repository    # JPA repository
 ┃ ┣ 📂 service       # Service interface & implementation
 ┗ ExceptionPocApplication.java  # Main Spring Boot application
```

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.5.6**
- **Spring Web** – RESTful API development
- **Spring Data JPA** – Persistence and ORM
- **Spring AOP** – Aspect Oriented Programming (logging)
- **H2 Database** – In-memory database for testing
- **Lombok** – Reduce boilerplate code
- **Hibernate Validator (Jakarta Validation)** – Request validation
- **ModelMapper** – DTO ↔ Entity mapping
- **Maven** – Build and dependency management
- **Postman** – API testing

---

## 📡 API Testing

A Postman collection is prepared for this project:  
[**Exception-POC Postman Collection**](https://www.postman.com/lunar-module-operator-48760766/workspace/springbootprojects/folder/39285790-af6b9462-6f44-474b-872f-220343056f3a?action=share&source=copy-link&creator=39285790&ctx=documentation)

---

## ⚙️ Setup Instructions

```bash
# 1. Clone the project
git clone https://github.com/alirizakaygusuz/spring-boot-feature-pocs.git
cd spring-boot-feature-pocs/exception-poc

# 2. Build and run
./mvnw clean install
./mvnw spring-boot:run
```

---

## ✍️ Author

**Ali Rıza Kaygusuz** – 🛠️ Java Backend Developer  
🌐 [GitHub Profile](https://github.com/alirizakaygusuz)  
💼 [LinkedIn Profile](https://www.linkedin.com/in/alirizakaygusuz)

---

## 📄 License

This project is licensed under the **MIT License**.  
📃 [MIT License Link](https://opensource.org/licenses/MIT)  

Feel free to fork, contribute, or use it freely in your own applications.
