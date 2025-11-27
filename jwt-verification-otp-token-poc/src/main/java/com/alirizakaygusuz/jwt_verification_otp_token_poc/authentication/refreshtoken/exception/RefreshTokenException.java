package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.exception;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.BaseException;

public class RefreshTokenException extends BaseException{
	public RefreshTokenException(RefreshTokenErrorType errorType , Object...args) {
		super(errorType,args);
	}

}
