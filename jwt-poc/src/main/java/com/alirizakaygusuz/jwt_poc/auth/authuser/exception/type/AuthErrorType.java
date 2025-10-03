package com.alirizakaygusuz.jwt_poc.auth.authuser.exception.type;

import static com.alirizakaygusuz.jwt_poc.auth.authuser.exception.type.AuthErrorCodes.*;

import org.springframework.http.HttpStatus;

import com.alirizakaygusuz.jwt_poc.common.exception.type.BaseErrorType;



public enum AuthErrorType  implements BaseErrorType{

	AUTH_USER_NOT_FOUND(AUTH_USER_NOT_FOUND_CODE, "error.auth.not_found", HttpStatus.NOT_FOUND),
    AUTH_USERNAME_ALREADY_EXISTS(AUTH_USERNAME_ALREADY_EXISTS_CODE, "error.auth.username_already_exists", HttpStatus.CONFLICT),
    AUTH_EMAIL_ALREADY_EXISTS(AUTH_EMAIL_ALREADY_EXISTS_CODE, "error.auth.email_already_exists", HttpStatus.CONFLICT),
    AUTH_INVALID_EMAIL(AUTH_INVALID_EMAIL_CODE, "error.auth.email_invalid", HttpStatus.BAD_REQUEST),
    AUTH_REQUIRED_FIELD(AUTH_REQUIRED_FIELD_CODE, "error.auth.required_field", HttpStatus.BAD_REQUEST),
    AUTH_INVALID_PASSWORD(AUTH_INVALID_PASSWORD_CODE, "error.auth.password_invalid", HttpStatus.BAD_REQUEST);

    private final int code;
    private final String i18nKey;
    private final HttpStatus status;

    AuthErrorType(int code, String i18nKey, HttpStatus status) {
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
