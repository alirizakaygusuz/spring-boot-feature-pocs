package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.service;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.dto.login.LoginRequest;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.dto.login.LoginResponse;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.model.AuthUser;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.service.LoginResponseFactory;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.config.OtpTokenProperties;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.dto.OtpTokenVerifyRequest;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.exception.OtpTokenErrorType;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.exception.OtpTokenException;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.model.OtpToken;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.repository.OtpTokenRepository;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.core.TokenPurpose;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.core.TokenStatus;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.notification.email.service.TokenEmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpTokenService {

	private final OtpTokenRepository otpTokenRepository;

	private final OtpTokenProperties otpTokenProperties;

	private final TokenEmailService emailService;
	
	private final LoginResponseFactory loginResponseFactory;
	
	@Qualifier("newTransactionTemplate")
	private final TransactionTemplate newTransactionTemplate;

	
	@Transactional
	public LoginResponse verifyOtpTokenCode(OtpTokenVerifyRequest request) {
		OtpToken otpToken = getActiveOtpTokenOrThrow(request.getOtpToken());

		
		if(otpToken.getAttemptCount() >= otpTokenProperties.getMaxAttempts()) {
			updateOtpTokenStatus(otpToken,TokenStatus.BLOCKED);
			throw new OtpTokenException(OtpTokenErrorType.OTP_TOKEN_IS_BLOCKED);
			
		}
		
		if (otpToken.getExpiryDateTime().isBefore(Instant.now())) {
			updateOtpTokenStatus(otpToken,TokenStatus.EXPIRED); 
            throw new OtpTokenException(OtpTokenErrorType.OTP_TOKEN_INVALID_OR_EXPIRED);
        }

		
		if(!otpToken.getOtpCode().equals(request.getOtpCode())) {
			increaseAttemptCount(otpToken);
			throw new OtpTokenException(OtpTokenErrorType.OTP_TOKEN_INVALID_OR_EXPIRED, request.getOtpCode());

		}
		
		
		otpToken.setTokenStatus(TokenStatus.USED);
		otpTokenRepository.save(otpToken);
		
		AuthUser authUser = otpToken.getAuthUser();
			
		return loginResponseFactory.createSuccessfulLoginResponse(authUser,otpToken.getIpAddress() , otpToken.getUserAgent());

	}
	
	private OtpToken getActiveOtpTokenOrThrow(String otpToken) {
		return otpTokenRepository.findByOtpTokenAndTokenStatus(otpToken , TokenStatus.ACTIVE)
				.orElseThrow(() -> new OtpTokenException(OtpTokenErrorType.OTP_TOKEN_NOT_FOUND));
	}


	private void updateOtpTokenStatus(OtpToken otpToken , TokenStatus tokenStatus) {
		try {
			newTransactionTemplate.executeWithoutResult(status ->{ 
				otpToken.setTokenStatus(tokenStatus);
				otpTokenRepository.save(otpToken);
			});
		}catch (Exception e) {
			throw new OtpTokenException(OtpTokenErrorType.OTP_TOKEN_UPDATE_FAILED);
		}
		
	}
	
	private void increaseAttemptCount(OtpToken otpToken) {
		try {
			newTransactionTemplate.executeWithoutResult(increase ->{
				otpToken.setAttemptCount(otpToken.getAttemptCount()+1);
				otpTokenRepository.save(otpToken);
					
			});
		}catch(Exception e) {
			throw new OtpTokenException(OtpTokenErrorType.OTP_TOKEN_UPDATE_FAILED);

		}
	}

	
	@Transactional
	public OtpToken createOtpToken(AuthUser authUser, LoginRequest loginRequest, TokenPurpose purpose) {
		OtpToken otpToken = buildOtpToken(authUser,loginRequest, purpose);

		return otpTokenRepository.save(otpToken);

	}
	
	public void sendEmail(OtpToken otpToken) {
		emailService.sendTokenEmail(otpToken, Locale.ENGLISH);

	}
	
	private OtpToken buildOtpToken(AuthUser authUser,LoginRequest loginRequest, TokenPurpose purpose) {
		String otpCode = generateOtpCode();
		String otpToken = UUID.randomUUID().toString();

		return OtpToken.builder()
				.authUser(authUser)
				.otpCode(otpCode)
				.otpToken(otpToken)
				.tokenPurpose(purpose)
				.tokenStatus(TokenStatus.ACTIVE)
				.expiryDateTime(Instant.now().plus(otpTokenProperties.getTtlSeconds(), ChronoUnit.SECONDS))
				.attemptCount(0).ipAddress(loginRequest.getIpAddress()).userAgent(loginRequest.getUserAgent()).build();
	}
	
	private String generateOtpCode() {
		SecureRandom secureRandom = new SecureRandom();
		int otpCode = secureRandom.nextInt(900_000) + 100_000;

		return String.format("%06d", otpCode);
	}


	
}
