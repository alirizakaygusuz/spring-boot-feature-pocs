package com.alirizakaygusuz.jwt_poc.auth.jwt.exception;

import com.alirizakaygusuz.jwt_poc.auth.jwt.exception.type.JwtErrorType;
import com.alirizakaygusuz.jwt_poc.common.exception.BaseException;

public class JwtCustomException  extends BaseException{

	public JwtCustomException(JwtErrorType type, Object... args) {
        super(type, args);
    }
}
