package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.validation.validator;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.validation.ValidEmailOrUsername;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EmailOrUsernameValidator implements ConstraintValidator<ValidEmailOrUsername , String>{

	 private static final String EMAIL_REGEX =
	            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"; 

	    private static final String USERNAME_REGEX =
	            "^[A-Za-z0-9._-]{3,20}$"; 
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value == null) {
			return false;
		}
		
		boolean isEmail = value.matches(EMAIL_REGEX);
		boolean isUsername = value.matches(USERNAME_REGEX);
		
		
		return isEmail || isUsername;
	}

}
