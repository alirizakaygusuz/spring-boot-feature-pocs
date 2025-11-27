package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.model.AuthUser;

@Repository
public interface AuthUserRepository extends JpaRepository<AuthUser, Long> {

	Optional<AuthUser> findByUsername(String username);

	Optional<AuthUser> findByEmail(String email);

	@Query("""
			    SELECT u
			    FROM AuthUser u
			    WHERE u.username = :identifier
			       OR u.email = :identifier
			""")
	Optional<AuthUser> findByUsernameOrEmail(String identifier);

}
