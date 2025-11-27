# 🔐 JWT Verification & OTP Token – Spring Boot POC

This project is a **Proof of Concept (POC)** showing a complete, secure and stateless authentication flow using:

- **Email Verification Token** (registration activation)
- **OTP (One-Time Password)** during login (2-step authentication)
- **JWT Access & Refresh Tokens with Rotation**
- **RBAC infrastructure** (roles, permissions, user–role, role–permission)
- **Module-based exception architecture**
- **i18n (Internationalization)**
- **Schedulers for token cleanup**
- **Profile-based seeders (dev & prod)**
- **AOP Logging + Global Exception Handling**

Everything is built with clean modular design, extensible architecture and production-grade patterns.

---

## 🚀 Features

### 🔥 Multi-Step Authentication Flow
- **Register → Verification Token → Activate account**
- **Login → OTP → Access/Refresh Tokens**

### 🔐 JWT Authentication & Authorization
- Stateless authentication (no sessions)
- Custom `JwtAuthenticationFilter`
- `JwtAccessDeniedHandler` (403), `JwtAuthenticationEntryPoint` (401)
- Access token + **rotating** refresh tokens

### 📡 Email Verification
- Verification token is generated on registration
- Sent via SMTP (Gmail)
- Account becomes active after verification
- Expired & used tokens cleaned by scheduler

### 📲 OTP (One-Time Password) Flow
- After username/password → OTP email is sent
- User receives:
```json
{
  "otpRequired": true,
  "otpExpiresInSeconds": 300,
  "otpVerificationToken": "xxxx-xxxx-xxxx"
}
```
- Verified via `/api/v1/auth/otp/verify`
- Then JWT token pair is issued

### 🛡️ Permission-Based RBAC Infrastructure
- `Role`, `Permission`, `RolePermission`, `UserRole` entities
- All permissions defined in `PredefinedPermission`
- Expandable architecture ready for:
  - `@PreAuthorize("hasAuthority('<permission>')")`

### 🌍 Internationalization (i18n)
- English & Turkish support
- Domain-based message bundles
- Error messages resolved by `Accept-Language`

### 🧩 Advanced Error Handling
- `BaseException` + `BaseErrorType`
- **ErrorModule** to enforce error-code ranges
- Each domain validates error codes (JWT, OTP, Verification, Email, etc.)

### 🧹 Schedulers
- Refresh token cleanup
- OTP cleanup
- Verification token cleanup

### 📬 Email Configuration (via `.env`)
- Clean `@ConfigurationProperties`-based mail configuration

---

## 📦 Implemented Modules

### 1️⃣ Authentication
Handles:
- Registration
- Email verification
- Login part-1 (credentials)
- Login part-2 (OTP)
- Token issuing

### 2️⃣ Verification Token Module
- Token-based account activation
- TTL + status
- Scheduler cleanup

### 3️⃣ OTP Token Module
- OTP generation
- Email sending
- Token + OTP validation
- Scheduler cleanup

### 4️⃣ Refresh Token Module
- Refresh token rotation
- Logout (revocation)
- Scheduler cleanup

### 5️⃣ Authorization (RBAC Infrastructure)
- Predefined roles & permissions
- Role-permission relationships
- User-role relationships
- Seeder support for dev & prod

### 6️⃣ Common Infrastructure
- Global exception handler
- Standard API response model
- LoggingAspect
- Jackson UTC config
- Auditing base entities

---

## 🌍 i18n (Internationalization) Structure

```
src/main/resources/i18n/
 ┣ auth/
 ┣ jwt/
 ┣ verification/
 ┣ otp/
 ┗ permission/
```

Example:

**error_auth_en.properties**
```
error.auth.invalid_credentials=Invalid username or password.
```

**error_auth_tr.properties**
```
error.auth.invalid_credentials=Kullanıcı adı veya şifre hatalı.
```

Locale selection example:

```
Accept-Language: tr
```

---

## 📡 Authentication Flow & Endpoints

### 🔑 1. Register
`POST /api/v1/auth/register`

Creates disabled user (`isEnabled=false`), sends verification email.

---

### 🔑 2. Verify Email
`POST /api/v1/auth/verify`

Activates account.

---

### 🔐 3. Login (Step 1 – Credentials)
`POST /api/v1/auth/login`

Two possible responses:

#### a) **Seeded dev users** (2FA disabled)
→ Directly receive JWT tokens.

#### b) **Normal users**
→ OTP required:

```json
{
  "otpRequired": true,
  "otpVerificationToken": "xxx",
  "otpExpiresInSeconds": 300
}
```

---

### 🔐 4. Login (Step 2 – OTP)
`POST /api/v1/auth/otp/verify`

Returns JWT access + refresh tokens.

```json
{
  "accessToken": "...",
  "refreshToken": "...",
  "expiresIn": 900
}
```

---

### 🔁 Refresh Token Rotation
`POST /api/v1/auth/refresh-token/rotate`

- Old token revoked  
- New token pair created  

---

### 🚪 Logout
`POST /api/v1/auth/logout`

Revokes the current refresh token.

---

## 👤 Seeded Accounts & Profiles

### 🧪 DEV Profile
Used when:

```
--spring.profiles.active=dev
```

**MockUserSeederService** runs automatically:

| Username | Password | Role        | 2FA |
|----------|----------|-------------|-----|
| admin    | admin123 | SUPER_ADMIN | OFF |
| user     | user123  | USER        | OFF |

### 🏭 PROD Profile
Used when:

```
--spring.profiles.active=prod
```


- Flyway enabled
- SQL migrations for:
  - roles  
  - permissions  
  - role-permission mapping
  - system admin & user
 
 | Username    | Password    | Role        | 2FA |
|--------------|-------------|-------------|-----|
| SYSTEM       | AliBaba!123 | SUPER_ADMIN | OFF |
| super_admin| | AliBaba!123 | USER        | OFF |


---

## ⚙️ Configuration

### `.env`
```env
JWT_SECRET_KEY=...
DB_URL=jdbc:mysql://localhost:3306/jwt-verification-otp-token-poc...
DB_USERNAME=root
DB_PASSWORD=12345

SYSTEM_AUTH_USER_NAME=system
SYSTEM_AUTH_USER_PASSWORD=change-me

MAIL_FROM=fraudscopeverify@gmail.com
MAIL_PASSWORD=your-app-password
MAIL_HOST=smtp.gmail.com
MAIL_PORT=587
MAIL_PROTOCOL=smtp
```

### EmailProperties
```java
@Configuration
@ConfigurationProperties(prefix = "spring.mail")
@Getter @Setter
public class EmailProperties {
    private String username;
}
```

---

## 🧹 Schedulers
- `RefreshTokenScheduler`
- `VerificationTokenScheduler`
- `OtpTokenScheduler`

---

## 🛠 Tech Stack
- Java 17  
- Spring Boot  
- Spring Security  
- JWT  
- MySQL  
- Flyway  
- Lombok  
- Jakarta Validation  
- AOP   
- i18n (Internationalization)  
- Maven   

---

## 📡 API Testing

A Postman collection is prepared for this project:
[**JWT-VERIFICATION-OTP-TOKEN-POC Postman Collection**](https://www.postman.com/lunar-module-operator-48760766/workspace/springbootprojects/folder/39285790-97a96f06-2e65-4e5f-b271-cfdae3856105?action=share&source=copy-link&creator=39285790&ctx=documentation)

---

## 🧪 Example Flow

1. **Register** – `POST /api/v1/auth/register`  
   - User registers, account is created as **disabled** (`enabled = false`)  
   - A **verification token** is generated and sent via email

2. **Email Verification** – `GET /api/v1/auth/verification-tokens/verify?token=<verificationToken>`  
   - Verification token is validated (not expired, not used, correct purpose)  
   - User account is activated (`enabled = true`)  
   - Verification token is marked as used / expired

3. **Login – Step 1 (Credentials)** – `POST /api/v1/auth/login`  
   - Request: **email + password**  
   - If credentials are valid and account is verified:  
     - An **OTP** is generated and sent (e.g. email/SMS)  
     - Response contains:  
       - `otpRequired = true`  
       - `otpVerificationToken` (short-lived, stateless step-2 token)  

4. **Login – Step 2 (OTP Verification)** – `POST /api/v1/auth/otp/verify`  
   - Request: `otpVerificationToken` + `otpCode`  
   - If OTP is valid and not expired:  
     - Issue **JWT `accessToken` + `refreshToken`**  
     - Tokens are bound to the user and stored with metadata (ip, userAgent, etc. if implemented)

5. **Access Secured Endpoints**  
   - Client sends: `Authorization: Bearer <accessToken>`  
   - Backend validates JWT and applies **permission-based RBAC**  
     - e.g. `@PreAuthorize("hasAuthority('E_USER_READ')")`

6. **Refresh Token Rotation** – `POST /api/v1/auth/refresh-token/rotate`  
   - Request: current `refreshToken`  
   - Backend checks that the refresh token is:
     - not expired  
     - not revoked  
     - not already used (rotation check)  
   - If valid:  
     - Marks current refresh token as **revoked/used**  
     - Issues a **new access/refresh token pair**  
   - Reusing an old refresh token is rejected (rotation protection)

7. **Logout** – `POST /api/v1/auth/logout`  
   - Request usually contains the current `refreshToken`  
   - That refresh token is **revoked**  
   - Further use of that token (refresh or logout again) is blocked

8. **Schedulers (Token Cleanup)**  
   - Periodic jobs clean up:
     - expired **verification tokens**  
     - expired **OTP tokens**  
     - expired / revoked **refresh tokens**  
   - Keeps the token tables small and the system clean in the long run



## ✍️ Author
**Ali Rıza Kaygusuz**  
💻 Java Backend Developer 
🌐 [GitHub Profile](https://github.com/alirizakaygusuz)  
💼 [LinkedIn Profile](https://www.linkedin.com/in/alirizakaygusuz)

---

## 📄 License

This project is licensed under the **MIT License**.

