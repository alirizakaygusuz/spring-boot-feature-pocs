package com.alirizakaygusuz.jwt_permission_based_poc.authorization.permission.exception.type;

import static com.alirizakaygusuz.jwt_permission_based_poc.authorization.permission.exception.type.PermissionErrorCodes.PERMISSION_NOT_FOUND_CODE;

import org.springframework.http.HttpStatus;

import com.alirizakaygusuz.jwt_permission_based_poc.common.exception.type.BaseErrorType;

public enum PermissionErrorType implements BaseErrorType {
	
	PERMISSION_NOT_FOUND(PERMISSION_NOT_FOUND_CODE, "error.permission.not_found", HttpStatus.NOT_FOUND);
	

    private final int code;
    private final String i18nKey;
    private final HttpStatus status;

    PermissionErrorType(int code, String i18nKey, HttpStatus status) {
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
