package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.notification.email.exception;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.exception.BaseException;

public class EmailException extends BaseException {

	public EmailException(EmailErrorType errorType, Object...args) {
		super(errorType, args);
	}

}
