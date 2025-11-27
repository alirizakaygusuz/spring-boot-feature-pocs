package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.exception;

import org.springframework.http.HttpStatus;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.BaseException;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.BaseErrorType;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.ErrorModule;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.ErrorModuleErrorType;

public enum OtpTokenErrorType implements BaseErrorType {
	
	OTP_TOKEN_NOT_FOUND(9001, "error.otp.token.not_found", HttpStatus.NOT_FOUND),
	OTP_TOKEN_INVALID_OR_EXPIRED(9002, "error.otp.token.invalid_or_expired", HttpStatus.BAD_REQUEST),
	OTP_TOKEN_IS_BLOCKED(9003, "error.otp.token.blocked", HttpStatus.BAD_REQUEST),
	OTP_TOKEN_UPDATE_FAILED(9003, "error.otp.token.update_failed", HttpStatus.INTERNAL_SERVER_ERROR);

	private final int code;
	private final String i18nKey;
	private final HttpStatus status;

	OtpTokenErrorType(int code, String i18nKey, HttpStatus status) {
		if (ErrorModule.OTP_TOKEN.contains(code)) {
			this.code = code;
			this.i18nKey = i18nKey;
			this.status = status;
		}else {
			throw new BaseException(ErrorModuleErrorType.OTP_TOKEN_EXCEPTION_CODE_NOT_IN_RANGE , code);
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
