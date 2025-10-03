package com.alirizakaygusuz.jwt_poc.auth.authuser.exception;

import com.alirizakaygusuz.jwt_poc.auth.authuser.exception.type.AuthErrorType;
import com.alirizakaygusuz.jwt_poc.common.exception.BaseException;

public class AuthException extends BaseException {
	public AuthException(AuthErrorType type, Object... args) {
        super(type, args);
    }
}
