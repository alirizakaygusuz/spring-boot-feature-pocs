package com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.permission.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.permission.model.Permission;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.permission.type.PermissionCategory;

@Repository
public interface PermissionRepository extends JpaRepository<Permission , Long>{

	boolean existsByPermissionKey(String key);

	List<Permission> findByCategory(PermissionCategory category);

}
