package com.alirizakaygusuz.jwt_verification_otp_token_poc.common.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.alirizakaygusuz.jwt_verification_otp_token_poc.common.validation.validator.EmailOrUsernameValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EmailOrUsernameValidator.class)
public @interface ValidEmailOrUsername{
	
    String message() default "Invalid email or username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
