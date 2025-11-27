-- V1__init_auth_schema.sql

-- 1) AUTH USERS
CREATE TABLE auth_users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME(3) NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at DATETIME(3),
    updated_by VARCHAR(255),
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    deleted_by VARCHAR(255),
    deleted_at DATETIME(3),

    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_enabled BOOLEAN NOT NULL DEFAULT FALSE,
    two_factor_enabled BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT pk_auth_users PRIMARY KEY (id),
    CONSTRAINT uq_auth_users_username UNIQUE (username),
    CONSTRAINT uq_auth_users_email UNIQUE (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- 2) ROLES
CREATE TABLE roles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME(3) NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at DATETIME(3),
    updated_by VARCHAR(255),
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    deleted_by VARCHAR(255),
    deleted_at DATETIME(3),

    name VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,

    CONSTRAINT pk_roles PRIMARY KEY (id),
    CONSTRAINT uq_roles_name UNIQUE (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- 3) PERMISSIONS
CREATE TABLE permissions (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME(3) NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at DATETIME(3),
    updated_by VARCHAR(255),
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    deleted_by VARCHAR(255),
    deleted_at DATETIME(3),

    permission_key VARCHAR(255) NOT NULL,
    description VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,

    CONSTRAINT pk_permissions PRIMARY KEY (id),
    CONSTRAINT uq_permissions_permission_key UNIQUE (permission_key)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- 4) ROLE_PERMISSIONS (RolePermission)
CREATE TABLE role_permissions (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME(3) NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at DATETIME(3),
    updated_by VARCHAR(255),
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    deleted_by VARCHAR(255),
    deleted_at DATETIME(3),

    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    granted_by VARCHAR(255) NOT NULL,
    granted_at DATETIME(3) NOT NULL,
    is_active BOOLEAN NOT NULL,
    note VARCHAR(255),

    CONSTRAINT pk_role_permissions PRIMARY KEY (id),
    CONSTRAINT uq_role_permissions_role_permission UNIQUE (role_id, permission_id),
    CONSTRAINT fk_role_permissions_role
        FOREIGN KEY (role_id) REFERENCES roles (id),
    CONSTRAINT fk_role_permissions_permission
        FOREIGN KEY (permission_id) REFERENCES permissions (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- 5) USER_ROLES (UserRole)
CREATE TABLE user_roles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME(3) NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at DATETIME(3),
    updated_by VARCHAR(255),
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    deleted_by VARCHAR(255),
    deleted_at DATETIME(3),

    auth_user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    CONSTRAINT pk_user_roles PRIMARY KEY (id),
    CONSTRAINT fk_user_roles_auth_user
        FOREIGN KEY (auth_user_id) REFERENCES auth_users (id),
    CONSTRAINT fk_user_roles_role
        FOREIGN KEY (role_id) REFERENCES roles (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- 6) REFRESH TOKENS
CREATE TABLE refresh_tokens (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME(3) NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at DATETIME(3),
    updated_by VARCHAR(255),

    token VARCHAR(512) NOT NULL,
    expiry_date DATETIME(3) NOT NULL,
    revoked BOOLEAN NOT NULL DEFAULT FALSE,
    revoked_at DATETIME(3),
    ip_address VARCHAR(255),
    user_agent VARCHAR(255),
    auth_user_id BIGINT NOT NULL,

    CONSTRAINT pk_refresh_tokens PRIMARY KEY (id),
    CONSTRAINT uq_refresh_tokens_token UNIQUE (token),
    CONSTRAINT fk_refresh_tokens_auth_user
        FOREIGN KEY (auth_user_id) REFERENCES auth_users (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


-- 7) VERIFICATION TOKENS
CREATE TABLE verification_tokens (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME(3) NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at DATETIME(3),
    updated_by VARCHAR(255),

    -- TokenBase fields
    token_purpose VARCHAR(255) NOT NULL,
    token_status VARCHAR(255) NOT NULL,
    expiry_date_time DATETIME(3) NOT NULL,

    verification_token VARCHAR(255) NOT NULL,
    auth_user_id BIGINT NOT NULL,

    CONSTRAINT pk_verification_tokens PRIMARY KEY (id),
    CONSTRAINT uq_verification_tokens_token UNIQUE (verification_token),
    CONSTRAINT uq_verification_tokens_auth_user UNIQUE (auth_user_id),
    CONSTRAINT fk_verification_tokens_auth_user
        FOREIGN KEY (auth_user_id) REFERENCES auth_users (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 8) OTP TOKENS
CREATE TABLE otp_tokens (
    id BIGINT NOT NULL AUTO_INCREMENT,
    created_at DATETIME(3) NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    updated_at DATETIME(3),
    updated_by VARCHAR(255),

    -- TokenBase fields
    token_purpose VARCHAR(255) NOT NULL,
    token_status VARCHAR(255) NOT NULL,
    expiry_date_time DATETIME(3) NOT NULL,

    otp_code VARCHAR(6) NOT NULL,
    otp_token VARCHAR(255) NOT NULL,
    attempt_count INT NOT NULL DEFAULT 0,
    auth_user_id BIGINT NOT NULL,
    ip_address VARCHAR(255),
    user_agent VARCHAR(255),

    CONSTRAINT pk_otp_tokens PRIMARY KEY (id),
    CONSTRAINT uq_otp_tokens_token UNIQUE (otp_token),
    CONSTRAINT fk_otp_tokens_auth_user
        FOREIGN KEY (auth_user_id) REFERENCES auth_users (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
