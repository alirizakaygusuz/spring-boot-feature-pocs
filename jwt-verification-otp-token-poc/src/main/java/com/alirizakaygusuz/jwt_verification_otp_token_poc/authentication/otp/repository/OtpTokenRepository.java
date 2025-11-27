package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.model.OtpToken;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.core.TokenStatus;


@Repository
public interface OtpTokenRepository extends JpaRepository<OtpToken , Long> {
	
	Optional<OtpToken> findByOtpCode(String otpCode);
	
	
	Optional<OtpToken> findByOtpTokenAndTokenStatus(String otpVerificationToken, TokenStatus tokenStatus);
	
	List<OtpToken> findAllByExpiryDateTimeBeforeAndTokenStatusNot(Instant expiryDateTime, TokenStatus tokenStatus);


	List<OtpToken> findAllByExpiryDateTimeBeforeAndTokenStatus(Instant expiryDateTime, TokenStatus active);

}
