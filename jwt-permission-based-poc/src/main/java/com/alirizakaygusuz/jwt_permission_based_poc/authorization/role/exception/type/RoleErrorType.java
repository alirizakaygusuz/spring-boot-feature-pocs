package com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.exception.type;

import static com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.exception.type.RoleErrorCodes.*;

import org.springframework.http.HttpStatus;

import com.alirizakaygusuz.jwt_permission_based_poc.common.exception.type.BaseErrorType;

public enum RoleErrorType implements BaseErrorType {

	ROLE_NOT_FOUND(ROLE_NOT_FOUND_CODE, "error.role.not_found", HttpStatus.NOT_FOUND),
	ADMIN_ROLE_NOT_FOUND(ADMIN_ROLE_NOT_FOUND_CODE, "error.role.admin_not_found", HttpStatus.NOT_FOUND),
	USER_ROLE_NOT_FOUND(USER_ROLE_NOT_FOUND_CODE, "error.role.user_not_found", HttpStatus.NOT_FOUND);

	private final int code;
	private final String i18nKey;
	private final HttpStatus status;

	RoleErrorType(int code, String i18nKey, HttpStatus status) {
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
