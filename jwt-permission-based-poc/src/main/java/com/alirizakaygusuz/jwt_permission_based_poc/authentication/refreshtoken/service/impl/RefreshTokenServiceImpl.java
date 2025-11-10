package com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.model.AuthUser;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.jwt.service.JwtService;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.dto.RefreshTokenRequest;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.dto.RefreshTokenResponse;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.exception.RefreshTokenException;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.exception.type.RefreshTokenErrorType;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.model.RefreshToken;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.repository.RefreshTokenRepository;
import com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.service.RefreshTokenService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class RefreshTokenServiceImpl implements RefreshTokenService {

	private final RefreshTokenRepository refreshTokenRepository;
	private final JwtService jwtService;

	@Getter
	@Value("${jwt.refresh.token.expiration-minutes}")
	private Long refreshTokenExpiration;

	
	
//	@PostConstruct
//	public void init() {
//        this.refreshTokenExpiration *= 60 * 1000;
//
//	}
//	
	private RefreshToken build(AuthUser authUser, String ipAddress, String userAgent) {
		return RefreshToken.builder().authUser(authUser).token(UUID.randomUUID().toString())
				.expiryDate(LocalDateTime.now().plusMinutes(refreshTokenExpiration)).ipAddress(ipAddress)
				.userAgent(userAgent).build();
	}

	private RefreshToken save(RefreshToken refreshToken) {
		return refreshTokenRepository.save(refreshToken);
	}

	@Override
	public RefreshToken create(AuthUser user, String ip, String agent) {
		RefreshToken token = build(user, ip, agent);
		return save(token);
	}

	@Override
	@Transactional
	public RefreshTokenResponse rotateToken(RefreshTokenRequest request) {
		RefreshToken refreshToken = findByRequestOrThrowError(request);

		if (refreshToken.isRevoked() || refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
			throw new RefreshTokenException(RefreshTokenErrorType.REFRESHTOKEN_INVALID);
		}

		markAsRevoked(refreshToken);

		RefreshToken rotatedRefreshToken = build(refreshToken.getAuthUser(), request.getIpAddress(),
				request.getUserAgent());

		save(rotatedRefreshToken);

		String accessToken = jwtService.generateAccessToken(refreshToken.getAuthUser());

		return new RefreshTokenResponse(accessToken, rotatedRefreshToken.getToken());
	}

	@Override
	@Transactional
	public String logout(RefreshTokenRequest request) {
		RefreshToken refreshToken = findByRequestOrThrowError(request);
		
		if(refreshToken.isRevoked() || refreshToken.isExpired()) {
			throw new RefreshTokenException(RefreshTokenErrorType.REFRESHTOKEN_INVALID);
		}

		markAsRevoked(refreshToken);
		save(refreshToken);

		return "Successfully logged out.";
	}

	private RefreshToken findByRequestOrThrowError(RefreshTokenRequest request) {
		return refreshTokenRepository.findByToken(request.getRefreshToken())
				.orElseThrow(() -> new RefreshTokenException(RefreshTokenErrorType.REFRESHTOKEN_NOT_FOUND));
	}

	private void markAsRevoked(RefreshToken refreshToken) {
		refreshToken.setRevoked(true);
		refreshToken.setRevokedAt(LocalDateTime.now());
	}
}
