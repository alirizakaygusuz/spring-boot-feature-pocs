# 🎯 AOP-POC – Spring Boot Aspect Oriented Programming

This project is a **Proof of Concept (POC)** built with **Spring Boot** to demonstrate and practice **Aspect Oriented Programming (AOP)**.  
It implements different **Aspects** for centralized **logging, performance monitoring, and validation**.

---

## 🚀 Features

- **LoggingAspect** → Logs method calls before, after returning, and when exceptions occur.  aa
- **PerformanceAspect** → Measures method execution time for performance tracking.  
- **ParamValidationAspect** → Validates controller method parameters with `@NotNullCustom` and `@NotEmptyCustom`.  
- **FieldValidationAspect** → Validates DTO fields with `@PositiveNumberCustom` and `@NotEmptyCustom`.  
- **Global Exception Handling** → Centralized exception handling with meaningful HTTP responses.

---

## 📦 Implemented Aspects

### 1️⃣ LoggingAspect
- **@Before** → Runs before method execution.  
- **@AfterReturning** → Runs after successful return.  
- **@AfterThrowing** → Runs when exceptions are thrown.  

**Purpose**: Centralized logging of service method entry, exit, and exceptions.

**Example Logs:**
```
[Before] Method: ProductServiceImpl.createProduct(..) called with args: [...]
[After Returning] Method: ProductServiceImpl.createProduct(..) returned: ProductResponse(name=Huawei XX6, code=123456)
[After Throwing] Method: OrderServiceImpl.updateOrder(..) threw exception: OrderNotFoundException
```

---

### 2️⃣ PerformanceAspect
- Uses **@Around** advice.  
- Wraps method execution and measures runtime.  

**Purpose**: Identify performance bottlenecks.

**Example Log:**
```
[Performance] Method: ProductServiceImpl.getAllProducts(..) executed in 38 ms
```

---

### 3️⃣ ParamValidationAspect
- Uses **@Before** advice.  
- Validates method parameters.  
- Supported custom annotations:
  - `@NotEmptyCustom` → Parameter must not be null or empty.  
  - `@NotNullCustom` → Parameter must not be null.  

**Purpose**: Validate controller path/query parameters.

**Example Usage:**
```java
@GetMapping("/{code}")
public ResponseEntity<ProductResponse> getProductByCode(
    @NotEmptyCustom @PathVariable String code) {
    return ResponseEntity.ok(productService.getProductByCode(code));
}
```

**Invalid Request Example:**
```
GET /api/v1/products/   (empty code)
❌ Throws: ParamValidationException("Product code cannot empty")
```

---

### 4️⃣ FieldValidationAspect
- Uses **@Before** advice.  
- Validates request body DTO fields.  
- Supported custom annotations:
  - `@PositiveNumberCustom` → Numeric fields must be positive.  
  - `@NotEmptyCustom` → String fields must not be empty.  

**Purpose**: Validate request body payload.

**Example Usage:**
```java
public class OrderCreateRequest {
    @NotEmptyCustom(message = "Product code cannot empty")
    private String productCode;

    @PositiveNumberCustom(message = "Price must be greater than 0")
    private double price;
}
```

**Invalid Request Example:**
```json
{
  "productCode": "123",
  "price": -10
}
```
```
❌ Throws: FieldValidationException("Price must be greater than 0")
```

---

## 📂 Project Structure

```
com.alirizakaygusuz.aop_poc
 ┣ 📂 common
 ┃ ┣ 📂 aspect       # Logging, Performance, Validation Aspects
 ┃ ┣ 📂 audit        # Audit base entities
 ┃ ┣ 📂 exception    # Global & custom exceptions
 ┃ ┣ 📂 mapper       # Base mapper interfaces
 ┃ ┣ 📂 response     # Standard API response wrappers
 ┃ ┣ 📂 validation   # Custom annotations & validators
 ┣ 📂 order
 ┃ ┣ 📂 controller   # REST controllers
 ┃ ┣ 📂 dto          # Request & Response DTOs
 ┃ ┣ 📂 service      # Business logic services
 ┃ ┣ 📂 mapper       # DTO ↔ Entity mappers
 ┃ ┣ 📂 model        # JPA entities
 ┃ ┣ 📂 exception    # Order-specific exceptions
 ┃ ┣ 📂 repository   # JPA repositories
 ┃ ┣ 📂 enums        # Domain enums
 ┣ 📂 product
 ┃ ┣ 📂 controller   # REST controllers
 ┃ ┣ 📂 dto          # Request & Response DTOs
 ┃ ┣ 📂 service      # Business logic services
 ┃ ┣ 📂 mapper       # DTO ↔ Entity mappers
 ┃ ┣ 📂 model        # JPA entities
 ┃ ┣ 📂 exception    # Product-specific exceptions
 ┃ ┣ 📂 repository   # JPA repositories
 ┗ AopPocApplication.java  # Main Spring Boot application
```

---

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.5.6**
- **Spring Web** – RESTful API development
- **Spring AOP** – Aspect Oriented Programming
- **Spring Data JPA** – Persistence and ORM
- **H2 Database** – In-memory database for testing
- **Lombok** – Reducing boilerplate code
- **JUnit & Spring Boot Test** – Unit and integration testing
- **Maven** – Build and dependency management
- **Postman** – API testing

---

## 📡 API Testing

A Postman collection is prepared for this project:  
[**AOP-POC Postman Collection**](https://www.postman.com/lunar-module-operator-48760766/springbootprojects/folder/98z71ow/aop-poc)

### 🔹 Example Requests

#### ✅ Create Product
```http
POST /api/v1/products
Content-Type: application/json

{
  "name": "Huawei XX6",
  "code": "123456"
}
```

#### ❌ Create Product (invalid - empty code)
```http
POST /api/v1/products
Content-Type: application/json

{
  "name": "Huawei XX6",
  "code": ""
}
```
Response:
```json
{
  "error": "FieldValidationException",
  "message": "Product code cannot empty"
}
```

#### ❌ Create Product (invalid - empty name)
```http
POST /api/v1/products
Content-Type: application/json

{
  "name": "",
  "code": "123456"
}
```
Response:
```json
{
  "error": "FieldValidationException",
  "message": "Product name cannot empty"
}
```

#### ✅ Create Order
```http
POST /api/v1/orders
Content-Type: application/json

{
  "productCode": "123456",
  "price": 1200
}
```

#### ❌ Create Order (invalid - negative price)
```http
POST /api/v1/orders
Content-Type: application/json

{
  "productCode": "123456",
  "price": -5
}
```
Response:
```json
{
  "error": "FieldValidationException",
  "message": "Price must be greater than 0"
}
```

#### ❌ Update Order (invalid - empty status)
```http
PATCH /api/v1/orders/123456
Content-Type: application/json

{
  "price": 1200,
  "status": ""
}
```
Response:
```json
{
  "error": "FieldValidationException",
  "message": "Order status cannot empty"
}
```

---

## ⚙️ Setup Instructions

```bash
# 1. Clone the project
git clone https://github.com/alirizakaygusuz/spring-boot-feature-pocs.git
cd spring-boot-feature-pocs/aop-poc

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
