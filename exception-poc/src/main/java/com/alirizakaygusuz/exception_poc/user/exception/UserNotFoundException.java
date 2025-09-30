package com.alirizakaygusuz.exception_poc.user.exception;

import com.alirizakaygusuz.exception_poc.common.exception.BaseException;
import com.alirizakaygusuz.exception_poc.user.exception.type.UserErrorType;

public class UserNotFoundException extends BaseException {

	public UserNotFoundException(Long id) {
        super(UserErrorType.USER_NOT_FOUND, id); // {0} = id
    }

	public UserNotFoundException(Throwable cause, Long id) {
        super(UserErrorType.USER_NOT_FOUND, cause, id);
    }
    
    
	

}
