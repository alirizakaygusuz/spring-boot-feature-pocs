package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.scheduler;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.core.TokenStatus;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.config.VerificationTokenProperties;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.model.VerificationToken;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.repository.VerificationTokenRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Profile({"dev","prod"})
@RequiredArgsConstructor
public class VerificationTokenCleanupScheduler {
	
	private final VerificationTokenRepository verificationTokenRepository;
	
	private final VerificationTokenProperties verificationTokenProperties;
	
	
	@Scheduled(fixedDelay = 240 * 60 * 1000) 
	@Transactional
	public void markExpiredVerificationTokens() {
		Instant threshold = Instant.now();
		
		List<VerificationToken> expiredVerificationTokens = verificationTokenRepository.findAllByExpiryDateTimeBeforeAndTokenStatus(threshold , TokenStatus.ACTIVE);
		
		expiredVerificationTokens.forEach(token -> token.setTokenStatus(TokenStatus.EXPIRED));
		
		log.info("Marked {} Verification Tokens as EXPIRED.", expiredVerificationTokens.size());
		
	}
	
	@Scheduled(cron = "0 25 3 * * 7")
	@Transactional
	public void cleanupExpiredVerificationTokens() {
		Instant threshold = Instant.now().minus(verificationTokenProperties.getDeleteAfterDays() , ChronoUnit.DAYS);
		
		List<VerificationToken> outdatedVerificationTokens = verificationTokenRepository.findAllByExpiryDateTimeBeforeAndTokenStatusNot(threshold , TokenStatus.ACTIVE);
		
		verificationTokenRepository.deleteAll(outdatedVerificationTokens);
		
		log.info("Deleted {} Verification Tokens as Expired.", outdatedVerificationTokens.size());

	}

}
