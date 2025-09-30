package com.alirizakaygusuz.exception_poc.common.exception.type;

import org.springframework.http.HttpStatus;

public interface BaseErrorType {
	String getCode();
	String getI18nKey();
    HttpStatus getStatus();
}
