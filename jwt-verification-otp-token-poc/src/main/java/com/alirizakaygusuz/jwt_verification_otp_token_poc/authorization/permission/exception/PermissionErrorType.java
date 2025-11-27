package com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.permission.exception;


import org.springframework.http.HttpStatus;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.BaseException;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.BaseErrorType;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.ErrorModule;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.ErrorModuleErrorType;

public enum PermissionErrorType implements BaseErrorType {

	PERMISSION_NOT_FOUND(7001, "error.permission.not_found", HttpStatus.NOT_FOUND);

	
	private final int code;
	private final String i18nKey;
	private final HttpStatus status;
	
	private PermissionErrorType(int code , String i18nKey , HttpStatus status) {
		
		if(ErrorModule.EMAIL.contains(code)) {
			this.code = code;
			this.i18nKey = i18nKey;
			this.status = status;
		}else {
			throw new BaseException(ErrorModuleErrorType.PERMISSION_EXCEPTION_CODE_NOT_IN_RANGE,code);
		}
		
		
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
