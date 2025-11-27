package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.verificationtoken.exception;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.BaseException;

public class VerificationTokenException extends BaseException{

	public VerificationTokenException(VerificationTokenErrorType errorType, Object...args) {
		super(errorType, args);
		
	}

}
