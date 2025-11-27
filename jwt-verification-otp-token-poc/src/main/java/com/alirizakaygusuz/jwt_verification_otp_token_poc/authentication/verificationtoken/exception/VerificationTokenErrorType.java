package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.exception;

import org.springframework.http.HttpStatus;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.BaseException;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.BaseErrorType;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.ErrorModule;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.ErrorModuleErrorType;

public enum VerificationTokenErrorType implements BaseErrorType {

	VERIFICATION_TOKEN_NOT_FOUND(8001, "error.verification.token.not_found", HttpStatus.NOT_FOUND),
	VERIFICATION_TOKEN_INVALID_OR_EXPIRED(8002, "error.verification.token.invalid_or_expired", HttpStatus.BAD_REQUEST);
	

	private final int code;
	private final String i18nKey;
	private final HttpStatus status;

	VerificationTokenErrorType(int code, String i18nKey, HttpStatus status) {
		if (ErrorModule.VERIFICATION_TOKEN.contains(code)) {
			this.code = code;
			this.i18nKey = i18nKey;
			this.status = status;
		}else {
			throw new BaseException(ErrorModuleErrorType.VERIFICATION_TOKEN_EXCEPTION_CODE_NOT_IN_RANGE , code);
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
