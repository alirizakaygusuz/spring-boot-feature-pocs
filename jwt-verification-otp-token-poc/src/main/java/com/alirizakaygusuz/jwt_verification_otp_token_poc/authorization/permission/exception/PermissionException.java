package com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.permission.exception;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.BaseException;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.ErrorModuleErrorType;

public class PermissionException extends BaseException {
	
	public PermissionException(PermissionErrorType errorType , Object... args) {
		super(errorType , args);
	}
	
	public PermissionException(ErrorModuleErrorType errorType , Object... args) {
		super(errorType , args);
	}
	
	
}
