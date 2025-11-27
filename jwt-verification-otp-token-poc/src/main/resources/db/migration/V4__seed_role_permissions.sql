-- V4__seed_role_permissions.sql

-- USER ROLE → USER_ACCOUNT_SELF_MANAGEMENT
INSERT INTO role_permissions (
    role_id,
    permission_id,
    granted_by,
    granted_at,
    is_active,
    created_at,
    created_by
)
SELECT
    r.id,
    p.id,
    'SYSTEM',        -- granted_by
    NOW(3),          -- granted_at
    TRUE,            -- is_active
    NOW(3),          -- created_at (audit)
    'SYSTEM'         -- created_by (audit)
FROM roles r
JOIN permissions p ON 1=1
WHERE r.name = 'USER'
  AND p.category = 'USER_ACCOUNT_SELF_MANAGEMENT'
  AND NOT EXISTS (
        SELECT 1
        FROM role_permissions rp
        WHERE rp.role_id = r.id
          AND rp.permission_id = p.id
  );


-- SUPER_ADMIN ROLE →
-- ADMIN_PERMISSION_MANAGEMENT + ADMIN_ROLE_MANAGEMENT + ADMIN_USER_MANAGEMENT
INSERT INTO role_permissions (
    role_id,
    permission_id,
    granted_by,
    granted_at,
    is_active,
    created_at,
    created_by
)
SELECT
    r.id,
    p.id,
    'SYSTEM',        -- granted_by
    NOW(3),          -- granted_at
    TRUE,            -- is_active
    NOW(3),          -- created_at
    'SYSTEM'         -- created_by
FROM roles r
JOIN permissions p ON 1=1
WHERE r.name = 'SUPER_ADMIN'
  AND p.category IN (
        'ADMIN_PERMISSION_MANAGEMENT',
        'ADMIN_ROLE_MANAGEMENT',
        'ADMIN_USER_MANAGEMENT'
    )
  AND NOT EXISTS (
        SELECT 1
        FROM role_permissions rp
        WHERE rp.role_id = r.id
          AND rp.permission_id = p.id
  );
