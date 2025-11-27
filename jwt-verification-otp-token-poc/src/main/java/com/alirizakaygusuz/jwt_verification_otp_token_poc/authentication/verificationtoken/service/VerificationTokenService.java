package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.model.AuthUser;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.repository.AuthUserRepository;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.core.TokenPurpose;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.core.TokenStatus;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.config.VerificationTokenProperties;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.exception.VerificationTokenErrorType;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.exception.VerificationTokenException;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.model.VerificationToken;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.repository.VerificationTokenRepository;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.BaseException;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.notification.email.service.TokenEmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VerificationTokenService {
	
	
	private final VerificationTokenProperties verificationTokenProperties;
	
	private final VerificationTokenRepository verificationTokenRepository;
	
	private final AuthUserRepository authUserRepository;
	
	private final TokenEmailService emailService;
	
	
	
	@Transactional(rollbackFor = BaseException.class,
    noRollbackFor = VerificationTokenException.class)
	public void verifyVerificationToken(String token) {
		VerificationToken verificationToken = getVerificationTokenOrThrow(token);
		
		validateVerificationTokenOrThrow((verificationToken));
				
		AuthUser user = verificationToken.getAuthUser();
		user.setEnabled(true); // Activate user!
		authUserRepository.save(user);

		verificationToken.setTokenStatus(TokenStatus.USED);
		verificationTokenRepository.save(verificationToken);
		
		
	}
	
	private void validateVerificationTokenOrThrow(VerificationToken verificationToken) {
	    if (verificationToken.getTokenStatus() != TokenStatus.ACTIVE) {
	        throw new VerificationTokenException(
	                VerificationTokenErrorType.VERIFICATION_TOKEN_INVALID_OR_EXPIRED);
	    }

	    if (verificationToken.getExpiryDateTime().isBefore(Instant.now())) {
	        verificationToken.setTokenStatus(TokenStatus.EXPIRED);
	        verificationTokenRepository.save(verificationToken);
	        throw new VerificationTokenException(
	                VerificationTokenErrorType.VERIFICATION_TOKEN_INVALID_OR_EXPIRED);
	    }
	}

	
	
	
	
	private VerificationToken getVerificationTokenOrThrow(String token) {
		return verificationTokenRepository.findByVerificationToken(token)
										   .orElseThrow(() -> new VerificationTokenException(VerificationTokenErrorType.VERIFICATION_TOKEN_NOT_FOUND , token));
	}


	@Transactional
	public void createVerificationTokenAndSendEmail(AuthUser authUser) {

		VerificationToken verificationToken =buildVerificationToken(authUser);
		
		VerificationToken savedVerificationToken = verificationTokenRepository.save(verificationToken);
		
		
		sendVerificationTokenEmail(savedVerificationToken);
														
	}
	
	
	private VerificationToken buildVerificationToken(AuthUser authUser) {
		return VerificationToken.builder()
				.verificationToken(UUID.randomUUID().toString())
				.authUser(authUser)
				.expiryDateTime(Instant.now().plus(verificationTokenProperties.getTtlMinutes(),ChronoUnit.MINUTES))
				.tokenPurpose(TokenPurpose.EMAIL_VERIFICATION)
				.tokenStatus(TokenStatus.ACTIVE)
				.build();

	}
	
	private void sendVerificationTokenEmail(VerificationToken verificationToken) {

		emailService.sendTokenEmail(verificationToken, Locale.ENGLISH);

	}
	
	
	

}
