package com.alirizakaygusuz.jwt_verification_otp_token_poc.authentication.refreshtoken.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RefreshTokenValidator.class)
public @interface ValidRefreshToken {
	
	String message() default "Invalid refresh token";
	
	 Class<?>[] groups() default {};

	 Class<? extends Payload>[] payload() default {};

}
