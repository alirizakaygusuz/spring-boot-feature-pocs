package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.authuser.exception;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.BaseException;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.ErrorModuleErrorType;

public class AuthException extends BaseException{

	public AuthException(AuthErrorType errorType , Object...args) {
		super(errorType,args);
	}

	public AuthException(ErrorModuleErrorType errorType, int code) {
		super(errorType,code);

	}
}
