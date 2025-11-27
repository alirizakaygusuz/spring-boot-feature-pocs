-- V2__seed_predefined_roles.sql

INSERT INTO roles (name, description, created_at, created_by)
VALUES
    ('SUPER_ADMIN', 'Full system access', NOW(3), 'SYSTEM'),
    ('USER', 'Standard user access', NOW(3), 'SYSTEM');
