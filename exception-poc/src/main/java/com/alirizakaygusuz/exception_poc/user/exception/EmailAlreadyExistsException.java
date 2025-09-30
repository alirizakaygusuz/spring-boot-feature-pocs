package com.alirizakaygusuz.exception_poc.user.exception;

import com.alirizakaygusuz.exception_poc.common.exception.BaseException;
import com.alirizakaygusuz.exception_poc.user.exception.type.UserErrorType;

public class EmailAlreadyExistsException extends BaseException{

	public EmailAlreadyExistsException(String email) {
        super(UserErrorType.EMAIL_ALREADY_EXISTS, email); // {0} = email
    }

	public EmailAlreadyExistsException(Throwable cause, String email) {
        super(UserErrorType.EMAIL_ALREADY_EXISTS, cause, email);
    }
}
