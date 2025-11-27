package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.jwt.exception;

import org.springframework.http.HttpStatus;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.BaseException;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.BaseErrorType;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.ErrorModule;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.ErrorModuleErrorType;

public enum JwtErrorType implements BaseErrorType {

	EXPIRED_TOKEN(2001, "error.jwt.expired", HttpStatus.UNAUTHORIZED),
	INVALID_TOKEN_SIGNATURE(2002, "error.jwt.invalid_signature", HttpStatus.UNAUTHORIZED),
	MALFORMED_TOKEN(2003, "error.jwt.malformed", HttpStatus.BAD_REQUEST),
	UNSUPPORTED_TOKEN(2004, "error.jwt.unsupported", HttpStatus.BAD_REQUEST),
	GENERAL_TOKEN_EXCEPTION(2005, "error.jwt.general_exception", HttpStatus.INTERNAL_SERVER_ERROR);

	private final int code;
	private final String i18nKey;
	private final HttpStatus status;

	JwtErrorType(int code, String i18nKey, HttpStatus status) {

		if (ErrorModule.JWT.contains(code)) {
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
