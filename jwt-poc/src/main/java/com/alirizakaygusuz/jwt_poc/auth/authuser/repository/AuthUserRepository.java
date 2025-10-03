package com.alirizakaygusuz.jwt_poc.auth.authuser.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.jwt_poc.auth.authuser.model.AuthUser;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser , Long> {

    @Query("SELECT u FROM AuthUser u WHERE u.username = :value OR u.email = :value")
	Optional<AuthUser> findByUsernameOrEmail(@Param("value") String value);
	
	boolean existsByUsername(String username);
	
	boolean existsByEmail(String email);
}
