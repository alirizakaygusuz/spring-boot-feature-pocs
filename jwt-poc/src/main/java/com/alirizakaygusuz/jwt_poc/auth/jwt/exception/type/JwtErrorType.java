package com.alirizakaygusuz.jwt_poc.auth.jwt.exception.type;



import static com.alirizakaygusuz.jwt_poc.auth.jwt.exception.type.JwtErrorCodes.*;

import org.springframework.http.HttpStatus;

import com.alirizakaygusuz.jwt_poc.common.exception.type.BaseErrorType;

public enum JwtErrorType implements BaseErrorType {

	JWT_EXPIRED_TOKEN(JWT_EXPIRED_TOKEN_CODE, HttpStatus.UNAUTHORIZED),
    JWT_MALFORMED_TOKEN(JWT_MALFORMED_TOKEN_CODE, HttpStatus.BAD_REQUEST),
    JWT_UNSUPPORTED_TOKEN(JWT_UNSUPPORTED_TOKEN_CODE, HttpStatus.BAD_REQUEST),
    JWT_GENERAL_TOKEN_EXCEPTION(JWT_GENERAL_TOKEN_EXCEPTION_CODE, HttpStatus.UNAUTHORIZED);
	
	
	private final int code;
	private final HttpStatus status;
	
	 JwtErrorType(int code, HttpStatus status) {
		 this.code = code;
		 this.status = status;
	 }

	@Override
	public Integer getCode() {
		
		return code;
	}

	@Override
	public String getI18nKey() {
		return null;
	}

	@Override
	public HttpStatus getStatus() {
		
		return status;
	}
		
}
