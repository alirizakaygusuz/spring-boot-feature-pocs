package com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.model.AuthUser;

@Repository
public interface AuthUserRepository  extends JpaRepository<AuthUser , Long>{

	
	@Query("""
	        SELECT au FROM AuthUser au
	        JOIN FETCH au.roleUsers ru
	        JOIN FETCH ru.role r
	        JOIN FETCH r.rolePermissions rp
	        JOIN FETCH rp.permission p
	        WHERE au.email = :input OR au.username = :input
	    """)
	    Optional<AuthUser> findUserWithRolesAndPermissions(String input);

	boolean existsByUsername(String username);

	boolean existsByEmail(String email);

	@Query("SELECT u FROM AuthUser u WHERE u.username = :input OR u.email = :input")
	Optional<AuthUser> findByUsernameOrEmail(@Param("input") String input);

}
