package com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.role.exception;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.BaseException;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.ErrorModuleErrorType;

public class RoleException extends BaseException {

	public RoleException(RoleErrorType errorType, Object...args) {
		super(errorType, args);
		
	}
	
	public RoleException(ErrorModuleErrorType errorType, Object...args) {
		super(errorType, args);
		
	}

}
