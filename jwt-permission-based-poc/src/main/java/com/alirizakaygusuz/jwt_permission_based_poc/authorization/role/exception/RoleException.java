package com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.exception;

import com.alirizakaygusuz.jwt_permission_based_poc.authorization.role.exception.type.RoleErrorType;
import com.alirizakaygusuz.jwt_permission_based_poc.common.exception.BaseException;

public class RoleException extends BaseException {
	
	public RoleException(RoleErrorType errorType , Object...args) {
		super(errorType, args);

	}

}
