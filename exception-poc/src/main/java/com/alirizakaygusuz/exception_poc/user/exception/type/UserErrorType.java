package com.alirizakaygusuz.exception_poc.user.exception.type;

import static com.alirizakaygusuz.exception_poc.user.exception.type.UserErrorCodes.*;

import org.springframework.http.HttpStatus;
import com.alirizakaygusuz.exception_poc.common.exception.type.BaseErrorType;

public enum UserErrorType implements BaseErrorType {

    USER_NOT_FOUND(USER_USER_NOT_FOUND, "error.user.not_found", HttpStatus.NOT_FOUND),
    USERNAME_ALREADY_EXISTS(USER_USER_ALREADY_EXISTS, "error.username.already_exists", HttpStatus.CONFLICT),
    EMAIL_ALREADY_EXISTS(USER_EMAIL_ALREADY_EXISTS, "error.user.email_exists", HttpStatus.CONFLICT),
    INVALID_EMAIL(USER_INVALID_EMAIL, "error.user.email_invalid", HttpStatus.BAD_REQUEST),
    REQUIRED_FIELD(USER_REQUIRED_FIELD, "error.user.required_field", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(USER_INVALID_PASSWORD, "error.user.password_invalid", HttpStatus.BAD_REQUEST);
    private final String code;
    private final String i18nKey;
    private final HttpStatus status;

    UserErrorType(String code, String i18nKey, HttpStatus status) {
        this.code = code;
        this.i18nKey = i18nKey;
        this.status = status;
    }

    @Override
    public String getCode() {
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
