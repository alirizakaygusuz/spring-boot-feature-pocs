package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.model.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken , Long>{

	Optional<RefreshToken> findByToken(String value);

	
	List<RefreshToken> findAllByExpiryDateBeforeAndRevokedFalse(Instant now);
	
	
	List<RefreshToken> findAllByRevokedTrueAndRevokedAtBefore(Instant threshHold);
	

}
