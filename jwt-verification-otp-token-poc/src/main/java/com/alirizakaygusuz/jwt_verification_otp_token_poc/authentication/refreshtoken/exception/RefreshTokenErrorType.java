package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.exception;

import org.springframework.http.HttpStatus;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.BaseException;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.BaseErrorType;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.ErrorModule;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.ErrorModuleErrorType;

public enum RefreshTokenErrorType implements BaseErrorType {

	REFRESHTOKEN_NOT_FOUND(3001, "error.refresh_token.not_found", HttpStatus.NOT_FOUND),
	REFRESHTOKEN_INVALID(3002, "error.refresh_token.invalid", HttpStatus.UNAUTHORIZED);

	private final int code;
	private final String i18nKey;
	private final HttpStatus status;

	RefreshTokenErrorType(int code, String i18nKey, HttpStatus status) {
		if (ErrorModule.REFRESH_TOKEN.contains(code)) {
			this.code = code;
			this.i18nKey = i18nKey;
			this.status = status;
		}else {
			throw new BaseException(ErrorModuleErrorType.JWT_EXCEPTION_CODE_NOT_IN_RANGE , code);
		}

	}

	@Override
	public Integer getCode() {

		return code;
	}

	@Override
	public String getI18nKey() {

		return i18nKey;
	}

	@Override
	public HttpStatus getStatus() {

		return status;
	}
}
