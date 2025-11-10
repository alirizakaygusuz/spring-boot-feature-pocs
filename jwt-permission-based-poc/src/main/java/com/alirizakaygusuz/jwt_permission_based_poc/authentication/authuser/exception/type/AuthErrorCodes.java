package com.alirizakaygusuz.jwt_permission_based_poc.authentication.authuser.exception.type;

public final class AuthErrorCodes {
	

	private AuthErrorCodes() {

	}

	public static final int AUTH_USER_NOT_FOUND_CODE = 1001;
	public static final int AUTH_USERNAME_ALREADY_EXISTS_CODE = 1002;
	public static final int AUTH_EMAIL_ALREADY_EXISTS_CODE = 1003;
	public static final int AUTH_INVALID_EMAIL_CODE = 1004;
	public static final int AUTH_REQUIRED_FIELD_CODE = 1005;
	public static final int AUTH_INVALID_PASSWORD_CODE = 1006;

}
