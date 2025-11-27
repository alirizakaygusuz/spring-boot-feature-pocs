package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.type;


import org.springframework.http.HttpStatus;

public enum ErrorModuleErrorType implements BaseErrorType {
	
	AUTHENTICATION_EXCEPTION_CODE_NOT_IN_RANGE(1000, "error.module.authentication", HttpStatus.INTERNAL_SERVER_ERROR),
	JWT_EXCEPTION_CODE_NOT_IN_RANGE(2000, "error.module.jwt", HttpStatus.INTERNAL_SERVER_ERROR),
	REFRESH_TOKEN_EXCEPTION_CODE_NOT_IN_RANGE(3000, "error.module.refresh_token", HttpStatus.INTERNAL_SERVER_ERROR),
	USER_EXCEPTION_CODE_NOT_IN_RANGE(4000, "error.module.user", HttpStatus.INTERNAL_SERVER_ERROR),
	ADMIN_EXCEPTION_CODE_NOT_IN_RANGE(5000, "error.module.admin", HttpStatus.INTERNAL_SERVER_ERROR),
	ROLE_EXCEPTION_CODE_NOT_IN_RANGE(6000, "error.module.role", HttpStatus.INTERNAL_SERVER_ERROR),
	PERMISSION_EXCEPTION_CODE_NOT_IN_RANGE(7000, "error.module.permission", HttpStatus.INTERNAL_SERVER_ERROR),
	VERIFICATION_TOKEN_EXCEPTION_CODE_NOT_IN_RANGE(8000, "error.module.verification", HttpStatus.INTERNAL_SERVER_ERROR),
	OTP_TOKEN_EXCEPTION_CODE_NOT_IN_RANGE(9000, "error.module.otp", HttpStatus.INTERNAL_SERVER_ERROR),
	EMAIL_EXCEPTION_CODE_NOT_IN_RANGE(10000, "error.module.email", HttpStatus.INTERNAL_SERVER_ERROR);

	private final int code;
	private final String i18nKey;
	private final HttpStatus status;
	
	
	private ErrorModuleErrorType(int code , String i18nKey , HttpStatus status) {
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
	}}
