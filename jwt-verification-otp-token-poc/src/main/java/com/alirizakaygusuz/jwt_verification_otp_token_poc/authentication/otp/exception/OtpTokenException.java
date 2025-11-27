package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.otp.exception;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.BaseException;

public class OtpTokenException extends BaseException {

	public OtpTokenException(OtpTokenErrorType errorType, Object...args) {
		super(errorType, args);
	}

}
