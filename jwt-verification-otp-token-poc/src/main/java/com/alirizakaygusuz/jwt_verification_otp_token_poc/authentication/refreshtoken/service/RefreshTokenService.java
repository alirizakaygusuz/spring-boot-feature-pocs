package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.model.AuthUser;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.jwt.service.JwtService;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.config.RefreshTokenProperties;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.dto.RefreshTokenRequest;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.dto.RefreshTokenResponse;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.exception.RefreshTokenErrorType;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.exception.RefreshTokenException;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.model.RefreshToken;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.repository.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
	
	private final RefreshTokenRepository refreshTokenRepository;
	
	private final JwtService jwtService;
	
	private final RefreshTokenProperties refreshTokenProperties;
	
	

	@Transactional
	public RefreshTokenResponse rotateToken(RefreshTokenRequest request) {
		RefreshToken refreshToken = findByRequestOrThrow(request);
		
		ensureRefreshTokenIsValid(refreshToken);
		refreshToken.revoke(Instant.now());
		
		RefreshToken rotatedRefreshToken = build(refreshToken.getAuthUser() , refreshToken.getIpAddress() ,refreshToken.getUserAgent());
		
		refreshTokenRepository.save(rotatedRefreshToken);
		
		String accessToken = jwtService.generateAccessToken(refreshToken.getAuthUser());
		
		
		return new RefreshTokenResponse(accessToken , rotatedRefreshToken.getToken());
	}
	
	
	@Transactional
	public String logout(RefreshTokenRequest request) {
		RefreshToken refreshToken = findByRequestOrThrow(request);
		
		ensureRefreshTokenIsValid(refreshToken);
		refreshToken.revoke(Instant.now());
		
		refreshTokenRepository.save(refreshToken);
		
		return "Successfully logged out";
	}
	
	
	public RefreshToken createAndSave(AuthUser authUser , String ipAddress  , String userAgent) {
		RefreshToken refreshToken = build(authUser , ipAddress , userAgent);
		
		return refreshTokenRepository.save(refreshToken);
		
	}
	
	private RefreshToken build(AuthUser authUser ,String ipAddress , String userAgent) {
		return RefreshToken.builder()
							.token(UUID.randomUUID().toString())
							.expiryDate(Instant.now().plus(refreshTokenProperties.getTtlDays(), ChronoUnit.DAYS))
							.revoked(false)
							.ipAddress(ipAddress)
							.userAgent(userAgent)
							.authUser(authUser)
						    .build();
						    
	}
	private void ensureRefreshTokenIsValid(RefreshToken refreshToken) {
		if (refreshToken.isRevoked() || refreshToken.isExpired()) {
			throw new RefreshTokenException(RefreshTokenErrorType.REFRESHTOKEN_INVALID);
		}
	}
	
	
	
	private RefreshToken findByRequestOrThrow(RefreshTokenRequest request) {
		return refreshTokenRepository.findByToken(request.getRefreshToken())
							.orElseThrow(() -> new RefreshTokenException(RefreshTokenErrorType.REFRESHTOKEN_NOT_FOUND , request.getRefreshToken()));
	}

}
