package com.alirizakaygusuz.aop_poc.common.aspect;

import java.lang.reflect.Field;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.alirizakaygusuz.aop_poc.common.validation.annotation.NotEmptyCustom;
import com.alirizakaygusuz.aop_poc.common.validation.annotation.PositiveNumberCustom;
import com.alirizakaygusuz.aop_poc.common.validation.exception.FieldValidationException;

import lombok.extern.slf4j.Slf4j;

@Aspect
@Component
@Slf4j
public class FieldValidationAspect {

	// 3. PositiveNumberCustom
	@Before("execution(* com.alirizakaygusuz.aop_poc..controller..*(..))")
	public void validatePositiveNumber(JoinPoint joinPoint) {
		Object[] args = joinPoint.getArgs();

		for (Object arg : args) {
			if(arg != null && !isPrimitiveOrWrapperClass(arg.getClass())) {
				validateRequestFields(arg);
			}
				
		}
	}

	private void validateRequestFields(Object request) {
		Field[] fields = request.getClass().getDeclaredFields();

		for (Field field : fields) {
			field.setAccessible(true);
			try {
				Object value = field.get(request);

				// @PositiveNumberCustom 
				if (field.isAnnotationPresent(PositiveNumberCustom.class)) {
					PositiveNumberCustom ann = field.getAnnotation(PositiveNumberCustom.class);
					if (!(value instanceof Number) || ((Number) value).doubleValue() <= 0) {
						throw new FieldValidationException(ann.message());
					}
				}
				
				// @NotEmptyCustom 
				if (field.isAnnotationPresent(NotEmptyCustom.class)) {
					NotEmptyCustom ann = field.getAnnotation(NotEmptyCustom.class);
					if (value == null || !(value instanceof String str) || str.trim().isEmpty()) {
				        throw new FieldValidationException(ann.message());
				    }
				}

			} catch (IllegalAccessException e) {
				log.error("Field cannot be accessed: {}", field.getName(), e);
			}
		}
	}
	
	
	
	//https://stackoverflow.com/questions/25039080/java-how-to-determine-if-type-is-any-of-primitive-wrapper-string-or-something
	private boolean isPrimitiveOrWrapperClass(Class<?> clazz) {
		
		  return clazz.isPrimitive()
	                || clazz.equals(String.class)
	                || Number.class.isAssignableFrom(clazz)
	                || clazz.equals(Boolean.class);
		  
		 //Second Solution
		//return ClassUtils.isPrimitiveOrWrapper(clazz);

	}

}
