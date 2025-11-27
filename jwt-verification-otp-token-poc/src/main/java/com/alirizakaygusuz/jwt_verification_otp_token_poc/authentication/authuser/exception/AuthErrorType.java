package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.exception;

import org.springframework.http.HttpStatus;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.BaseErrorType;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.ErrorModule;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.ErrorModuleErrorType;

public enum AuthErrorType implements BaseErrorType {

	AUTH_USER_NOT_FOUND(1001, "error.auth.not_found", HttpStatus.NOT_FOUND),
	AUTH_USERNAME_ALREADY_EXISTS(1002, "error.auth.username_already_exists", HttpStatus.CONFLICT),
	AUTH_EMAIL_ALREADY_EXISTS(1003, "error.auth.email_already_exists", HttpStatus.CONFLICT),
	AUTH_INVALID_EMAIL(1004, "error.auth.email_invalid", HttpStatus.BAD_REQUEST),
	AUTH_REQUIRED_FIELD(1005, "error.auth.required_field", HttpStatus.BAD_REQUEST),
	AUTH_INVALID_PASSWORD(1006, "error.auth.password_invalid", HttpStatus.BAD_REQUEST),
	AUTH_USER_NOT_VERIFIED(1007, "error.auth.user_not_verified", HttpStatus.BAD_REQUEST);

	private final int code;
	private final String i18nKey;
	private final HttpStatus status;

	AuthErrorType(int code, String i18nKey, HttpStatus status) {

		if (ErrorModule.AUTHENTICATION.contains(code)) {
			this.code = code;
			this.i18nKey = i18nKey;
			this.status = status;
		}else {
			throw new AuthException(ErrorModuleErrorType.AUTHENTICATION_EXCEPTION_CODE_NOT_IN_RANGE , code);
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
