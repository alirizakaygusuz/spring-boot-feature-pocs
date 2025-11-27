package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.jwt.exception;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.BaseException;

public class JwtException extends BaseException {
	public JwtException(JwtErrorType errorType, Object...args) {
		super(errorType , args);
	}

}
