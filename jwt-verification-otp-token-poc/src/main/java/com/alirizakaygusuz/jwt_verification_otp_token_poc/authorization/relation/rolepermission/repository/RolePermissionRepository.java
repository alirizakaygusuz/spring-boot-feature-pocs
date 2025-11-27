package com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.relation.rolepermission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.permission.model.Permission;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.relation.rolepermission.model.RolePermission;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.role.model.Role;

@Repository
public interface RolePermissionRepository extends JpaRepository<RolePermission , Long> {

	boolean existsByRoleAndPermission(Role role, Permission permission);
}
