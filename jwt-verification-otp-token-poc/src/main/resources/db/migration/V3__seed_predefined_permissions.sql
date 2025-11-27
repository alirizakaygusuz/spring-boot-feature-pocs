-- V3__seed_predefined_permissions.sql

INSERT INTO permissions (
    permission_key,
    description,
    category,
    created_at,
    created_by
)
VALUES
    -- ===== USER SELF MANAGEMENT =====
    ('user:self:view', 'View own profile', 'USER_ACCOUNT_SELF_MANAGEMENT', NOW(3), 'SYSTEM'),
    ('user:self:update', 'Update own profile', 'USER_ACCOUNT_SELF_MANAGEMENT', NOW(3), 'SYSTEM'),
    ('user:self:delete', 'Delete own account', 'USER_ACCOUNT_SELF_MANAGEMENT', NOW(3), 'SYSTEM'),

    -- ===== ADMIN - USER MANAGEMENT =====
    ('admin:user:create', 'Create a new user', 'ADMIN_USER_MANAGEMENT', NOW(3), 'SYSTEM'),
    ('admin:user:update', 'Update any user', 'ADMIN_USER_MANAGEMENT', NOW(3), 'SYSTEM'),
    ('admin:user:delete', 'Delete any user', 'ADMIN_USER_MANAGEMENT', NOW(3), 'SYSTEM'),
    ('admin:user:view', 'View a specific user', 'ADMIN_USER_MANAGEMENT', NOW(3), 'SYSTEM'),
    ('admin:user:view_all', 'View all users', 'ADMIN_USER_MANAGEMENT', NOW(3), 'SYSTEM'),

    -- ===== ADMIN - USER PERMISSION MANAGEMENT =====
    ('admin:user:permission:assign', 'Assign permission(s) to a user', 'ADMIN_PERMISSION_MANAGEMENT', NOW(3), 'SYSTEM'),
    ('admin:user:permission:remove', 'Remove permission(s) from a user', 'ADMIN_PERMISSION_MANAGEMENT', NOW(3), 'SYSTEM'),

    -- ===== ADMIN - ROLE MANAGEMENT =====
    ('admin:role:create', 'Create a new role', 'ADMIN_ROLE_MANAGEMENT', NOW(3), 'SYSTEM'),
    ('admin:role:update', 'Update existing role', 'ADMIN_ROLE_MANAGEMENT', NOW(3), 'SYSTEM'),
    ('admin:role:delete', 'Delete a role', 'ADMIN_ROLE_MANAGEMENT', NOW(3), 'SYSTEM'),
    ('admin:role:view', 'View role details', 'ADMIN_ROLE_MANAGEMENT', NOW(3), 'SYSTEM'),
    ('admin:role:view_all', 'View all roles', 'ADMIN_ROLE_MANAGEMENT', NOW(3), 'SYSTEM'),

    -- ===== ADMIN - PERMISSION MANAGEMENT =====
    ('admin:permission:view_all', 'View all permissions', 'ADMIN_PERMISSION_MANAGEMENT', NOW(3), 'SYSTEM');
