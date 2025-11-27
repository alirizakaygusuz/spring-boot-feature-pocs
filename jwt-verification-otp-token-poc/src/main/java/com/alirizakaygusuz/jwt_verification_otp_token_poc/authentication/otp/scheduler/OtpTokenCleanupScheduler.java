package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.scheduler;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.config.OtpTokenProperties;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.model.OtpToken;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.repository.OtpTokenRepository;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.token.core.TokenStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Profile({"dev","prod"})
@RequiredArgsConstructor
public class OtpTokenCleanupScheduler {
	
	private final OtpTokenRepository otpTokenRepository;
	private final OtpTokenProperties otpTokenProperties;
	
	@Scheduled(fixedDelay = 180000)
	@Transactional
	public void markExpiredOtpTokens() {
		Instant threshold =Instant.now();
		
		List<OtpToken> expiredOtpTokens = otpTokenRepository.findAllByExpiryDateTimeBeforeAndTokenStatus(threshold , TokenStatus.ACTIVE);
		
		expiredOtpTokens.forEach(token -> token.setTokenStatus(TokenStatus.EXPIRED));
		
		log.info("Marked {} OTP tokens as EXPIRED.", expiredOtpTokens.size());

		
	}
	
	@Scheduled(cron = "0 15 3 * * 7")
	@Transactional
	public void cleanUpExpiredOtpTokens() {
		Instant threshold = Instant.now().minus(otpTokenProperties.getDeleteAfterDays() , ChronoUnit.DAYS);
		
		List<OtpToken> outdatedOtpTokens = otpTokenRepository.findAllByExpiryDateTimeBeforeAndTokenStatusNot(threshold , TokenStatus.ACTIVE);
		
		otpTokenRepository.deleteAll(outdatedOtpTokens);
		
		log.info("Deleted {} OTP tokens as Expired.",outdatedOtpTokens.size());
	}

}
