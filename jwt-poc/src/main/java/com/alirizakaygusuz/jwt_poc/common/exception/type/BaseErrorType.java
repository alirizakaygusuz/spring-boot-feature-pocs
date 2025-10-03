package com.alirizakaygusuz.jwt_poc.common.exception.type;

import org.springframework.http.HttpStatus;


public interface BaseErrorType {

	Integer getCode();
	String getI18nKey();
	HttpStatus getStatus();
}
