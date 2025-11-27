package com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.permission.type;


public enum PredefinedPermission {

	// ===== USER SELF MANAGEMENT =====
    USER_SELF_VIEW("user:self:view", "View own profile", PermissionCategory.USER_ACCOUNT_SELF_MANAGEMENT),
    USER_SELF_UPDATE("user:self:update", "Update own profile", PermissionCategory.USER_ACCOUNT_SELF_MANAGEMENT),
    USER_SELF_DELETE("user:self:delete", "Delete own account", PermissionCategory.USER_ACCOUNT_SELF_MANAGEMENT),

    // ===== ADMIN - USER MANAGEMENT =====
    ADMIN_USER_CREATE("admin:user:create", "Create a new user", PermissionCategory.ADMIN_USER_MANAGEMENT),
    ADMIN_USER_UPDATE("admin:user:update", "Update any user", PermissionCategory.ADMIN_USER_MANAGEMENT),
    ADMIN_USER_DELETE("admin:user:delete", "Delete any user", PermissionCategory.ADMIN_USER_MANAGEMENT),
    ADMIN_USER_VIEW("admin:user:view", "View a specific user", PermissionCategory.ADMIN_USER_MANAGEMENT),
    ADMIN_USER_VIEW_ALL("admin:user:view_all", "View all users", PermissionCategory.ADMIN_USER_MANAGEMENT),

    // ===== ADMIN - USER PERMISSION MANAGEMENT =====
    ADMIN_USER_PERMISSION_ASSIGN("admin:user:permission:assign", "Assign permission(s) to a user", PermissionCategory.ADMIN_PERMISSION_MANAGEMENT),
    ADMIN_USER_PERMISSION_REMOVE("admin:user:permission:remove", "Remove permission(s) from a user", PermissionCategory.ADMIN_PERMISSION_MANAGEMENT),

    // ===== ADMIN - ROLE MANAGEMENT =====
    ADMIN_ROLE_CREATE("admin:role:create", "Create a new role", PermissionCategory.ADMIN_ROLE_MANAGEMENT),
    ADMIN_ROLE_UPDATE("admin:role:update", "Update existing role", PermissionCategory.ADMIN_ROLE_MANAGEMENT),
    ADMIN_ROLE_DELETE("admin:role:delete", "Delete a role", PermissionCategory.ADMIN_ROLE_MANAGEMENT),
    ADMIN_ROLE_VIEW("admin:role:view", "View role details", PermissionCategory.ADMIN_ROLE_MANAGEMENT),
    ADMIN_ROLE_VIEW_ALL("admin:role:view_all", "View all roles", PermissionCategory.ADMIN_ROLE_MANAGEMENT),

    // ===== ADMIN - PERMISSION MANAGEMENT =====
    ADMIN_PERMISSION_VIEW_ALL("admin:permission:view_all", "View all permissions", PermissionCategory.ADMIN_PERMISSION_MANAGEMENT);


    private final String key;
    private final String description;
    private final PermissionCategory category;

    PredefinedPermission(String key, String description, PermissionCategory category) {
        this.key = key;
        this.description = description;
        this.category = category ;
    }

    public String getKey() {
        return key;
    }

    public String getDescription() {
        return description;
    }
    
    public PermissionCategory getCategory() {
    	return category;
    }
}
