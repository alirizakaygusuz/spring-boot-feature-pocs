package com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.exception;

import com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.exception.type.AuthErrorType;
import com.alirizakaygusuz.jwt_permission_based_poc.common.exception.BaseException;

public class AuthException extends BaseException{

	public AuthException(AuthErrorType errorType, Object...args) {
		super(errorType, args);
		
	}

}
