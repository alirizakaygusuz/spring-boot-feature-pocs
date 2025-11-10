package com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.exception;

import com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.exception.type.RefreshTokenErrorType;
import com.alirizakaygusuz.jwt_permission_based_poc.common.exception.BaseException;

public class RefreshTokenException extends BaseException {
	
	public RefreshTokenException(RefreshTokenErrorType errorType , Object...args ) {
		super(errorType , args);
	}

}
