package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.validation;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.model.RefreshToken;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.repository.RefreshTokenRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class RefreshTokenValidator implements ConstraintValidator<ValidRefreshToken, String> {

	private final RefreshTokenRepository refreshTokenRepository;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		if (value == null || value.isBlank()) {
			return true; 
		}

		var optional = refreshTokenRepository.findByToken(value);

		if (optional.isEmpty()) {
			return violation(context, "Refresh token not found");
		}

		RefreshToken token = optional.get();

		if (token.isRevoked()) {
			return violation(context, "Refresh token revoked");
		}

		if (token.isExpired()) {
			return violation(context, "Refresh token expired");
		}

		return true;
	}

	private boolean violation(ConstraintValidatorContext context, String msg) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
		return false;
	}
}
