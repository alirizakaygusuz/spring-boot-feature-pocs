package com.alirizakaygusuz.aop_poc.common.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER , ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PositiveNumberCustom {
	String message() default "Number has to be positive";
}
