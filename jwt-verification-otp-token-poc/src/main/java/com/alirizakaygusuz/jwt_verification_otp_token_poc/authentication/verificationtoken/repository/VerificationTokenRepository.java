package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.core.TokenStatus;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.model.VerificationToken;


@Repository
public interface VerificationTokenRepository extends JpaRepository<VerificationToken , Long> {
	
	Optional<VerificationToken> findByVerificationToken(String verificationToken);
	
	
	List<VerificationToken> findAllByExpiryDateTimeBeforeAndTokenStatus(Instant expiryDateTime, TokenStatus tokenStatus);
	
	List<VerificationToken> findAllByExpiryDateTimeBeforeAndTokenStatusNot(Instant expiryDateTime, TokenStatus tokenStatus);

}
