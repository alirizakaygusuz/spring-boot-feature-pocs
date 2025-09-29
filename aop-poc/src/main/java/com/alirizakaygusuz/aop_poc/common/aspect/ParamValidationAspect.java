package com.alirizakaygusuz.aop_poc.common.aspect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.alirizakaygusuz.aop_poc.common.validation.annotation.NotEmptyCustom;
import com.alirizakaygusuz.aop_poc.common.validation.annotation.NotNullCustom;
import com.alirizakaygusuz.aop_poc.common.validation.exception.ParamValidationException;

import lombok.extern.slf4j.Slf4j;

//https://faizxmohammed.medium.com/creating-custom-annotations-in-java-with-reflection-and-aop-135e6920668e
//https://cemayan.medium.com/spring-aop-ile-custom-annotation-yapımı-method-profiling-error-handling-48a8f997e6fe

@Aspect
@Component
@Slf4j
public class ParamValidationAspect {

	// 1. NotEmptyParam
	@Before("execution(* com.alirizakaygusuz.aop_poc..controller..*(..))")
	public void validateNotEmpty(JoinPoint joinPoint) {
		validateMethodParameters(joinPoint, NotEmptyCustom.class);
	}

	// 2. NotNullParam
	@Before("execution(* com.alirizakaygusuz.aop_poc..controller..*(..))")
	public void validateNotNull(JoinPoint joinPoint) {
		validateMethodParameters(joinPoint, NotNullCustom.class);
	}

	private void validateMethodParameters(JoinPoint joinPoint, Class<? extends Annotation> annotationClass) {
		Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
		// Run time
		Object[] args = joinPoint.getArgs();
		// compile time
		Parameter[] parameters = method.getParameters();

		for (int i = 0; i < parameters.length; i++) {
			if (!parameters[i].isAnnotationPresent(annotationClass)) {
				continue; 
			}

			Annotation ann = parameters[i].getAnnotation(annotationClass);
			Object arg = args[i];

			if (ann instanceof NotEmptyCustom notEmpty) {
				validateNotEmpty(arg, notEmpty);
			} else if (ann instanceof NotNullCustom notNullCustom) {
				validateNotNull(arg, notNullCustom);
			}
		}

	}

	private void validateNotEmpty(Object arg, NotEmptyCustom notEmpty) {
		if (arg == null || arg.toString().trim().isEmpty()) {
			throw new ParamValidationException(notEmpty.message());
		}
	}

	private void validateNotNull(Object arg, NotNullCustom notNullCustom) {
		if (arg == null) {
			throw new ParamValidationException(notNullCustom.message());
		}
	}

}
