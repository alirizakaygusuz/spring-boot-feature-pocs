package com.alirizakaygusuz.jwt_permission_based_poc.authorization.permission.exception;

import com.alirizakaygusuz.jwt_permission_based_poc.authorization.permission.exception.type.PermissionErrorType;
import com.alirizakaygusuz.jwt_permission_based_poc.common.exception.BaseException;

public class PermissionException extends BaseException{
	
	public PermissionException(PermissionErrorType errorType, Object...args) {
		super(errorType ,args);
	}

}
