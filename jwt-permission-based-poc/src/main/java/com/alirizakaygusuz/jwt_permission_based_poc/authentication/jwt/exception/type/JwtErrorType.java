package com.alirizakaygusuz.jwt_permission_based_poc.authentication.jwt.exception.type;

import static com.alirizakaygusuz.jwt_permission_based_poc.authentication.jwt.exception.type.JwtErrorCodes.JWT_EXPIRED_TOKEN_CODE;
import static com.alirizakaygusuz.jwt_permission_based_poc.authentication.jwt.exception.type.JwtErrorCodes.JWT_GENERAL_TOKEN_EXCEPTION_CODE;
import static com.alirizakaygusuz.jwt_permission_based_poc.authentication.jwt.exception.type.JwtErrorCodes.JWT_INVALID_TOKEN_SIGNATURE_CODE;
import static com.alirizakaygusuz.jwt_permission_based_poc.authentication.jwt.exception.type.JwtErrorCodes.JWT_MALFORMED_TOKEN_CODE;
import static com.alirizakaygusuz.jwt_permission_based_poc.authentication.jwt.exception.type.JwtErrorCodes.JWT_UNSUPPORTED_TOKEN_CODE;

import org.springframework.http.HttpStatus;

import com.alirizakaygusuz.jwt_permission_based_poc.common.exception.type.BaseErrorType;

public enum JwtErrorType implements BaseErrorType{

	EXPIRED_TOKEN(JWT_EXPIRED_TOKEN_CODE, "error.jwt.expired", HttpStatus.UNAUTHORIZED),
	INVALID_TOKEN_SIGNATURE(JWT_INVALID_TOKEN_SIGNATURE_CODE, "error.jwt.invalid_signature", HttpStatus.UNAUTHORIZED),
	MALFORMED_TOKEN(JWT_MALFORMED_TOKEN_CODE, "error.jwt.malformed", HttpStatus.BAD_REQUEST),
	UNSUPPORTED_TOKEN(JWT_UNSUPPORTED_TOKEN_CODE, "error.jwt.unsupported", HttpStatus.BAD_REQUEST),
	GENERAL_TOKEN_EXCEPTION(JWT_GENERAL_TOKEN_EXCEPTION_CODE, "error.jwt.general_exception", HttpStatus.INTERNAL_SERVER_ERROR);
	
	private final int code;
    private final String i18nKey;
    private final HttpStatus status;

    JwtErrorType(int code, String i18nKey, HttpStatus status) {
        this.code = code;
        this.i18nKey = i18nKey;
        this.status = status;
    }
	

	@Override
	public Integer getCode() {
	
		return code;
	}

	@Override
	public String getI18nKey() {
	
		return i18nKey;
	}

	@Override
	public HttpStatus getStatus() {
		
		return status;
	}

}
