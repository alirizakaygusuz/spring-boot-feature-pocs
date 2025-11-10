package com.alirizakaygusuz.jwt_permission_based_poc.authentication.jwt.exception;

import com.alirizakaygusuz.jwt_permission_based_poc.authentication.jwt.exception.type.JwtErrorType;
import com.alirizakaygusuz.jwt_permission_based_poc.common.exception.BaseException;

public class JwtException extends BaseException{
	
	public JwtException(JwtErrorType errorType , Object...args) {
		super(errorType, args);
	}

}
