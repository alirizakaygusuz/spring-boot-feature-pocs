package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.scheduler;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.config.RefreshTokenProperties;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.model.RefreshToken;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.repository.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Profile({"dev","prod"})
@Slf4j
public class RefreshTokenScheduler {
	
	private final RefreshTokenRepository refreshTokenRepository;
	
	
	private final RefreshTokenProperties refreshTokenProperties;
	
	
	@Scheduled(cron = "0 15 3 * * *")
	@Transactional
	public void revokeExpiredRefreshTokens() {
		Instant now = Instant .now();
		
		List<RefreshToken> expiredTokens = refreshTokenRepository.findAllByExpiryDateBeforeAndRevokedFalse(now);
		
		expiredTokens.forEach(token -> {
			token.revoke(now);
		});
		
		refreshTokenRepository.saveAll(expiredTokens);
		
	    log.info("Revoked {} expired refresh tokens.", expiredTokens.size());	
	}
	
	
	@Scheduled(cron = "0 30 3 * * *")
	@Transactional
	public void cleanupExpiredTokens() {
		Instant threshold = Instant.now().minus(refreshTokenProperties.getCleanupDays(),  ChronoUnit.DAYS); 
		
		List<RefreshToken> tokensToDelete = refreshTokenRepository.findAllByRevokedTrueAndRevokedAtBefore(threshold);
		
		refreshTokenRepository.deleteAll(tokensToDelete);
		
		log.info("Cleaned up {} old revoked refresh tokens.", tokensToDelete.size());

	}
	

}
