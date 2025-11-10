package com.alirizakaygusuz.jwt_permission_based_poc.common.exception.type;

import org.springframework.http.HttpStatus;

public interface BaseErrorType {

	Integer getCode();

	String getI18nKey();

	HttpStatus getStatus();

}
