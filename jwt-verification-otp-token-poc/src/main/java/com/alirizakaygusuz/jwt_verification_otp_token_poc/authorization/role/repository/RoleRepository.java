package com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.role.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.role.model.Role;


@Repository
public interface RoleRepository extends JpaRepository<Role , Long> {

	Optional<Role> findByName(String name);
	
	
	boolean existsByName(String name);
	
	
}
