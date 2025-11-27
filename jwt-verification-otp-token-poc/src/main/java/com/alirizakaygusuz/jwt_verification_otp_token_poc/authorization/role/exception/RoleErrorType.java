package com.alirizakaygusuz.jwt_verification_otp_token_poc.authorization.role.exception;

import org.springframework.http.HttpStatus;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.BaseException;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.BaseErrorType;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.ErrorModule;
import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type.ErrorModuleErrorType;

public enum RoleErrorType implements BaseErrorType {

	ROLE_NOT_FOUND(6001, "error.role.not_found", HttpStatus.NOT_FOUND),
	ADMIN_ROLE_NOT_FOUND(6002, "error.role.admin_not_found", HttpStatus.NOT_FOUND),
	USER_ROLE_NOT_FOUND(6003, "error.role.user_not_found", HttpStatus.NOT_FOUND);

	private final int code;
	private final String i18nKey;
	private final HttpStatus status;

	RoleErrorType(int code, String i18nKey, HttpStatus status) {
		if(ErrorModule.ROLE.contains(code)) {
			this.code = code;
			this.i18nKey = i18nKey;
			this.status = status;
		}else {
			throw new BaseException(ErrorModuleErrorType.ROLE_EXCEPTION_CODE_NOT_IN_RANGE, code);
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
