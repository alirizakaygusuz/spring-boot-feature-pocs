package com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.exception.type;

import static com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.exception.type.RefreshTokenErrorCodes.REFRESHTOKEN_INVALID_CODE;
import static com.alirizakaygusuz.jwt_permission_based_poc.authentication.refreshtoken.exception.type.RefreshTokenErrorCodes.REFRESHTOKEN_NOT_FOUND_CODE;

import org.springframework.http.HttpStatus;

import com.alirizakaygusuz.jwt_permission_based_poc.common.exception.type.BaseErrorType;

public enum RefreshTokenErrorType implements BaseErrorType {


	REFRESHTOKEN_NOT_FOUND(REFRESHTOKEN_NOT_FOUND_CODE, "error.refreshtoken.not_found", HttpStatus.NOT_FOUND),
	REFRESHTOKEN_INVALID(REFRESHTOKEN_INVALID_CODE, "error.refreshtoken.invalid", HttpStatus.UNAUTHORIZED);
	

    private final int code;
    private final String i18nKey;
    private final HttpStatus status;

    RefreshTokenErrorType(int code, String i18nKey, HttpStatus status) {
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
