package com.alirizakaygusuz.exception_poc.user.exception;

import com.alirizakaygusuz.exception_poc.common.exception.BaseException;
import com.alirizakaygusuz.exception_poc.user.exception.type.UserErrorType;

public class UsernameAlreadyExistsException extends BaseException {

	public UsernameAlreadyExistsException(String username) {
        super(UserErrorType.USERNAME_ALREADY_EXISTS, username); // {0} = username
    }

	public UsernameAlreadyExistsException(Throwable cause, String username) {
        super(UserErrorType.USERNAME_ALREADY_EXISTS, cause, username);
    }
}
