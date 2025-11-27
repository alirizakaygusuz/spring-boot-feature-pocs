-- V5.1__seed_system_user.sql

-- SYSTEM USER (If Not Exists)
INSERT INTO auth_users (
    created_at,
    created_by,
    updated_at,
    updated_by,
    deleted,
    deleted_by,
    deleted_at,
    username,
    email,
    first_name,
    last_name,
    password,
    is_enabled,
    two_factor_enabled
)
SELECT
    NOW(3)            AS created_at,
    'SYSTEM'          AS created_by,
    NULL              AS updated_at,
    NULL              AS updated_by,
    0                 AS deleted,
    NULL              AS deleted_by,
    NULL              AS deleted_at,
    'SYSTEM'          AS username,
    'system@poc.local' AS email,
    'System'          AS first_name,
    'User'            AS last_name,
    -- BCRYPT HASH : example: $2a$10$...
    '$2a$12$nSwsJh41ds0bFSAck4Zjg.IZG7Lx38L3Kn/KwBkS3LnBClzec9KFC' AS password,
    1                 AS is_enabled,
    0                 AS two_factor_enabled
FROM DUAL
WHERE NOT EXISTS (
    SELECT 1 FROM auth_users WHERE username = 'SYSTEM'
);


-- User -> System User
INSERT INTO user_roles (
    created_at,
    created_by,
    updated_at,
    updated_by,
    deleted,
    deleted_by,
    deleted_at,
    auth_user_id,
    role_id
)
SELECT
    NOW(3)        AS created_at,
    'SYSTEM'      AS created_by,
    NULL          AS updated_at,
    NULL          AS updated_by,
    0             AS deleted,
    NULL          AS deleted_by,
    NULL          AS deleted_at,
    u.id          AS auth_user_id,
    r.id          AS role_id
FROM auth_users u
JOIN roles r ON r.name = 'USER'
WHERE u.username = 'SYSTEM'
  AND NOT EXISTS (
      SELECT 1
      FROM user_roles ur
      WHERE ur.auth_user_id = u.id
        AND ur.role_id = r.id
  );
