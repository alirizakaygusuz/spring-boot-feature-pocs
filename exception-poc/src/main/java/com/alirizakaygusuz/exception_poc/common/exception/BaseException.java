package com.alirizakaygusuz.exception_poc.common.exception;

import org.springframework.http.HttpStatus;

import com.alirizakaygusuz.exception_poc.common.exception.type.BaseErrorType;

import lombok.Getter;

@Getter
public abstract class BaseException extends RuntimeException {

	private final String code;
	private final String i18nKey;
	private final HttpStatus status;
	private final Object[] args;

	protected BaseException(BaseErrorType errorType, Object... args) {
        super(errorType.getI18nKey());
        this.code = errorType.getCode();
        this.i18nKey = errorType.getI18nKey();
        this.status = errorType.getStatus();
        this.args = args; 
    }

    protected BaseException(BaseErrorType errorType, Throwable cause, Object... args) {
        super(errorType.getI18nKey(), cause);
        this.code = errorType.getCode();
        this.i18nKey = errorType.getI18nKey();
        this.status = errorType.getStatus();
        this.args = args; 
    }

}
