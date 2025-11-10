package com.alirizakaygusuz.jwt_permission_based_poc.authorization.bootstrap.seeder;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.alirizakaygusuz.jwt_permission_based_poc.authorization.permission.exception.PermissionException;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.permission.exception.type.PermissionErrorType;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.permission.model.Permission;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.permission.repository.PermissionRepository;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.permission.type.PermissionCategory;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.relation.rolepermission.model.RolePermission;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.relation.rolepermission.repository.RolePermissionRepository;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.exception.RoleException;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.exception.type.RoleErrorType;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.model.Role;
import com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.repository.RoleRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class RolePermissionSeederService {

    private final PermissionRepository permissionRepository;
    private final RoleRepository roleRepository;
    private final RolePermissionRepository rolePermissionRepository;

    @Transactional
    public void seed() {
        log.info("Seeding Role-Permission relations...");

        // USER-ROLE
        log.info("Seeding USER Role-Permission relations...");

        Role userRole = getRoleOrThrow("USER");
        List<Permission> userPermissions = permissionRepository.findByCategory(PermissionCategory.USER_ACCOUNT_SELF_MANAGEMENT);
        log.info("Permissions fetched for USER.");

        asssignPermissionsToRole(userRole, userPermissions);
        log.info("Seeded USER Role-Permission relations.");

        // ADMIN-ROLE
        log.info("Seeding ADMIN Role-Permission relations...");

        Role adminRole = getRoleOrThrow("SUPER_ADMIN");

        List<Permission> adminPermissionManagement = getPermissionsOrThrow(PermissionCategory.ADMIN_PERMISSION_MANAGEMENT);
        List<Permission> adminRoleManagement = getPermissionsOrThrow(PermissionCategory.ADMIN_ROLE_MANAGEMENT);
        List<Permission> adminUserManagement = getPermissionsOrThrow(PermissionCategory.ADMIN_USER_MANAGEMENT);
        log.info("Permissions fetched for ADMIN.");

        List<Permission> adminPermissions = new ArrayList<>();

        adminPermissions.addAll(adminPermissionManagement);
        adminPermissions.addAll(adminRoleManagement);
        adminPermissions.addAll(adminUserManagement);

        asssignPermissionsToRole(adminRole, adminPermissions);
        log.info("Seeded ADMIN Role-Permission relations.");
    }

    private Role getRoleOrThrow(String roleName) {
        return roleRepository.findByName(roleName)
                .orElseThrow(() -> new RoleException(RoleErrorType.ROLE_NOT_FOUND, roleName));
    }

    private List<Permission> getPermissionsOrThrow(PermissionCategory category) {
        log.debug("Fetching permissions for category: {}", category);

        List<Permission> permissions = permissionRepository.findByCategory(category);

        if (permissions.isEmpty()) {
            throw new PermissionException(
                PermissionErrorType.PERMISSION_NOT_FOUND,
                "No permissions found for category: " + category.name()
            );
        }

        return permissions;
    }

    private void asssignPermissionsToRole(Role role, List<Permission> permissions) {
        List<RolePermission> rolePermissions = new ArrayList<>();

        for (Permission p : permissions) {
            if (!rolePermissionRepository.existsByRoleAndPermission(role, p)) {
                RolePermission rolePermission = RolePermission.builder()
                        .role(role)
                        .permission(p)                    
                        .build();

                rolePermissions.add(rolePermission);
            }
        }

        if (rolePermissions.isEmpty()) {
            log.info("No new permissions to assign for role '{}'.", role.getName());
        }

        rolePermissionRepository.saveAll(rolePermissions);
    }
}
